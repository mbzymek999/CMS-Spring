package cms.domain.company.service;

import cms.api.company.task.ReadCompanyTasksResponse;
import cms.api.company.task.TaskCompanyReadModel;
import cms.api.company.task.TaskRequest;
import cms.domain.company.entity.Company;
import cms.domain.company.entity.Task;
import cms.domain.company.repository.TaskRepository;
import cms.domain.company.serviceImpl.TaskCompanyServiceImpl;
import cms.domain.employee.entity.Employee;
import cms.domain.employee.repository.EmployeeRepository;
import cms.domain.user.entity.User;
import cms.domain.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskCompanyService implements TaskCompanyServiceImpl {

    private final TaskRepository repository;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    public TaskCompanyService(TaskRepository repository, UserRepository userRepository, EmployeeRepository employeeRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
    }

    public String addNewTask(TaskRequest taskRequest, Long employeeId, String idClient) {

        User user = userRepository.findByIdClient(idClient).orElse(null);

        Company company = user.getCompanyUser();
        checkIfCompanyExist(company);

        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        checkIfEmployeeExist(employee);


        // Create new task
        Task task = new Task(
                taskRequest.getName(),
                taskRequest.getType(),
                taskRequest.getDescription(),
                taskRequest.getDateTo().plusDays(1)
        );

        task.setCreatedDate(LocalDate.now());
        task.setStatusTask(0);
        task.setCompanyTask(company);
        task.setEmployeeTask(employee);
        repository.save(task);
        return "Ok";
    }

    public ReadCompanyTasksResponse readCompanyTasks(Pageable page, String idClient) {
        List<TaskCompanyReadModel> list = repository.findAllByCompanyTask_User_IdClient(page, idClient).stream().map(TaskCompanyReadModel::new).collect(Collectors.toList());
        Page<Task> pageInformation = repository.findAllByCompanyTask_User_IdClient(page, idClient);
        return new ReadCompanyTasksResponse(pageInformation, list);
    }

    public ReadCompanyTasksResponse readByStatusTask(Pageable page, String idClient, int statusTask) {
        List<TaskCompanyReadModel> list = repository.findAllByCompanyTask_User_IdClientAndStatusTask(page, idClient, statusTask).stream().map(TaskCompanyReadModel::new).collect(Collectors.toList());
        Page<Task> pageInformation = repository.findAllByCompanyTask_User_IdClientAndStatusTask(page, idClient, statusTask);
        return new ReadCompanyTasksResponse(pageInformation, list);
    }

    public ReadCompanyTasksResponse readAllWithStatusTask(Pageable page, String idClient, int statusTask) {
        if ((statusTask < 0 || statusTask > 2)) {
            return readCompanyTasks(page, idClient);
        } else {
            return readByStatusTask(page, idClient, statusTask);
        }
    }

    public List<Task> getAllTasks(String idClient) {
        return repository.findAllByCompanyTask_User_IdClient(idClient);
    }

    public void checkIfCompanyExist(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("Firma nie istnieje");
        }
    }

    public void checkIfEmployeeExist(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Pracownik nie istnieje");
        }
    }

}
