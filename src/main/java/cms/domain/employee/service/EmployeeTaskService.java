package cms.domain.employee.service;

import cms.api.employee.task.EmployeeTaskReadModel;
import cms.api.employee.task.UpdateTaskRequest;
import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.entity.Task;
import cms.domain.company.repository.TaskRepository;
import cms.domain.company.service.TaskCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeTaskService {

    private final TaskRepository repository;
    Logger logger = LoggerFactory.getLogger(TaskCompanyService.class);

    public EmployeeTaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeTaskReadModel> readEmployeeTasks(int statusTask) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();
        return repository.findAllByEmployeeTask_User_IdAndStatusTask(userImpl.getId(), statusTask).stream().map(EmployeeTaskReadModel::new).collect(Collectors.toList());
    }

    public EmployeeTaskReadModel updateTask(int id, UpdateTaskRequest updateTaskRequest) {
        Task task = repository.findById(id).orElseThrow();
        updateTaskRequest.getTask().updateEntity(task);
        repository.save(task);
        return new EmployeeTaskReadModel(task);
    }

}
