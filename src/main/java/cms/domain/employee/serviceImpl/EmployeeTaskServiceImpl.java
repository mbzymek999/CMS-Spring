package cms.domain.employee.serviceImpl;

import cms.api.employee.task.EmployeeTaskReadModel;
import cms.api.employee.task.EmployeeTaskResponse;
import cms.api.employee.task.UpdateTaskResponse;
import org.springframework.data.domain.Pageable;

public interface EmployeeTaskServiceImpl {

    EmployeeTaskResponse readEmployeeTasks(Pageable page, String idClient, int statusTask);

    EmployeeTaskReadModel updateTask(int id, UpdateTaskResponse updateTaskRequest);
}
