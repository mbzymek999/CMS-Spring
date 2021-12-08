package cms.domain.company.service;

import cms.api.company.task.ReadCompanyTasksResponse;
import cms.api.company.task.TaskCompanyReadModel;
import cms.api.company.task.TaskRequest;
import cms.domain.company.entity.Company;
import cms.domain.company.entity.Task;
import cms.domain.company.repository.TaskRepository;
import cms.domain.employee.entity.Employee;
import cms.domain.employee.repository.EmployeeRepository;
import cms.domain.user.entity.User;
import cms.domain.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskCompanyService {

    private final TaskRepository repository;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    Logger logger = LoggerFactory.getLogger(TaskCompanyService.class);

    public TaskCompanyService(TaskRepository repository, UserRepository userRepository, EmployeeRepository employeeRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
    }

    public String addNewTask(TaskRequest taskRequest, Long employeeId, Long idUser) {

        User user = userRepository.findById(idUser).orElse(null);

        Company company = user.getCompanyUser();
        checkIfCompanyExist(company);

        Employee employee = employeeRepository.findById(employeeId).orElse(null);
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

    public ReadCompanyTasksResponse readCompanyTasks(Pageable page, Long id) {
        List<TaskCompanyReadModel> list = repository.findAllByCompanyTask_User_Id(page, id).stream().map(TaskCompanyReadModel::new).collect(Collectors.toList());
        Page<Task> pageInformation = repository.findAllByCompanyTask_User_Id(page, id);
        return new ReadCompanyTasksResponse(pageInformation, list);
    }

    public List<Task> getAllTasks(Long id) {
        return repository.findAllByCompanyTask_User_Id(id);
    }

    public void checkIfCompanyExist(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("Firma nie istnieje");
        }
    }
    public void checkIfEmployeeExist(Employee employee) {
        if(employee == null){
            throw new IllegalArgumentException("Pracownik nie istnieje");
        }
    }

}
