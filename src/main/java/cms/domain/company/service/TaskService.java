package cms.domain.company.service;

import cms.api.company.dto.CompanyTaskReadModel;
import cms.api.company.dto.TaskReadModel;
import cms.api.company.request.TaskRequest;
import cms.api.employee.dto.EmployeeTaskReadModel;
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

import java.util.List;
import java.util.stream.Collectors;

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
        if(company == null){
            System.out.println("Firma nie istnieje");
        }
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if(employee == null){
            System.out.println("Pracownik nie istnieje");
        }
        Task task = taskRequest.getTask().toTask();
        task.setCompanyTask(company);
        task.setEmployeeTask(employee);
        repository.save(task);
        return "Ok";
    }

    public List<TaskReadModel> readAll() {
        return repository.findAll().stream().map(TaskReadModel::new).collect(Collectors.toList());
    }

    public List<CompanyTaskReadModel> readCompanyTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();
        return repository.findAllByCompanyTask_User_Id(userImpl.getId()).stream().map(CompanyTaskReadModel::new).collect(Collectors.toList());
    }

    public List<EmployeeTaskReadModel> readEmployeeTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();
        return repository.findAllByEmployeeTask_User_Id(userImpl.getId()).stream().map(EmployeeTaskReadModel::new).collect(Collectors.toList());
    }
}
