package cms.api.employee.task;

import cms.config.security.services.UserDetailsImpl;
import cms.domain.employee.serviceImpl.EmployeeTaskServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmployeeTaskController {

    private final EmployeeTaskServiceImpl employeeTaskServiceImpl;

    public EmployeeTaskController(EmployeeTaskServiceImpl employeeTaskServiceImpl) {
        this.employeeTaskServiceImpl = employeeTaskServiceImpl;
    }

    @GetMapping
    @RequestMapping("/employee/tasks/{statusTask}")
    EmployeeTaskResponse readCompanyTasks(Pageable page, @PathVariable int statusTask, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return employeeTaskServiceImpl.readEmployeeTasks(page, userDetails.getIdClient(), statusTask);
    }

    @RequestMapping(value = "/employee/task/update/{id}", method = RequestMethod.PUT)
    EmployeeTaskReadModel updateTask(@PathVariable(value = "id") Integer id, @RequestBody UpdateTaskResponse updateTaskRequest) {
        return employeeTaskServiceImpl.updateTask(id, updateTaskRequest);
    }
}
