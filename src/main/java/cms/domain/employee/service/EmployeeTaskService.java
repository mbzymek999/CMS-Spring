package cms.domain.employee.service;

import cms.api.employee.task.EmployeeTaskReadModel;
import cms.api.employee.task.EmployeeTaskResponse;
import cms.api.employee.task.UpdateTaskResponse;
import cms.domain.company.entity.Task;
import cms.domain.company.repository.TaskRepository;
import cms.domain.company.service.TaskCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    public EmployeeTaskResponse readEmployeeTasks(Pageable page, String idClient, int statusTask) {
        List<EmployeeTaskReadModel> list = repository.findAllByEmployeeTask_User_IdClientAndStatusTask(page, idClient, statusTask).stream().map(EmployeeTaskReadModel::new).collect(Collectors.toList());
        Page<Task> pageInformation = repository.findAllByEmployeeTask_User_IdClientAndStatusTask(page, idClient, statusTask);
        return new EmployeeTaskResponse(pageInformation, list);
    }

    public EmployeeTaskReadModel updateTask(int id, UpdateTaskResponse updateTaskRequest) {
        Task task = repository.findById(id).orElseThrow();
        updateTaskRequest.updateEntity(task);
        repository.save(task);
        return new EmployeeTaskReadModel(task);
    }

}
