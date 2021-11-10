package cms.domain.company.service;

import cms.api.company.request.TaskRequest;
import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.entity.Company;
import cms.domain.company.entity.Task;
import cms.domain.company.repository.CompanyRepository;
import cms.domain.company.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final CompanyRepository companyRepository;
    Logger logger = LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepository repository, CompanyRepository companyRepository) {
        this.repository = repository;
        this.companyRepository = companyRepository;
    }

    public String addNewTask(TaskRequest taskRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();

        Company company = companyRepository.findById(userImpl.getId()).orElse(null);

        if(company == null){
            System.out.println("Company is null");
        }

        Task task = taskRequest.getTask().toTask();

        task.setCompanyTask(company);

        repository.save(task);

        return "Ok";
    }
}
