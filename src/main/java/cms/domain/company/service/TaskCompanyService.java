package cms.domain.company.service;

import cms.api.company.task.TaskCompanyReadModel;
import cms.api.company.task.TaskRequest;
import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.entity.Company;
import cms.domain.company.entity.Task;
import cms.domain.company.repository.TaskRepository;
import cms.domain.employee.entity.Employee;
import cms.domain.employee.repository.EmployeeRepository;
import cms.domain.user.entity.User;
import cms.domain.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public String addNewTask(TaskRequest taskRequest, Long employeeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();
        User user = userRepository.findById(userImpl.getId()).orElse(null);

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
        task.setAccepted(false);
        task.setCompanyTask(company);
        task.setEmployeeTask(employee);
        repository.save(task);
        return "Ok";
    }

    public List<TaskCompanyReadModel> readCompanyTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();
        return repository.findAllByCompanyTask_User_Id(userImpl.getId()).stream().map(TaskCompanyReadModel::new).collect(Collectors.toList());
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
