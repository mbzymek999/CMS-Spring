package cms.domain.company.service;

import cms.api.company.request.TaskRequest;
import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.entity.Company;
import cms.domain.company.entity.Task;
import cms.domain.company.repository.CompanyRepository;
import cms.domain.company.repository.TaskRepository;
import cms.domain.employee.entity.Employee;
import cms.domain.employee.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    Logger logger = LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepository repository, CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.repository = repository;
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public String addNewTask(TaskRequest taskRequest, Long employeeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();

        Company company = companyRepository.findById(userImpl.getId()).orElse(null);

        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if(company == null){
            System.out.println("Company is null");
        }

        Task task = taskRequest.getTask().toTask();

        task.setCompanyTask(company);
        task.setEmployeeTask(employee);

        repository.save(task);

        return "Ok";
    }
}
