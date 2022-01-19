package cms.api.employee.task;

import cms.config.security.services.UserDetailsImpl;
import cms.domain.employee.service.EmployeeTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmployeeTaskController {

    private final EmployeeTaskService service;
    Logger logger = LoggerFactory.getLogger(EmployeeTaskController.class);
    public EmployeeTaskController(EmployeeTaskService service) {
        this.service = service;
    }

    @GetMapping
    @RequestMapping("/employee/tasks/{statusTask}")
    EmployeeTaskResponse readCompanyTasks(Pageable page, @PathVariable int statusTask, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return service.readEmployeeTasks(page, userDetails.getIdClient(), statusTask);
    }

    @RequestMapping(value = "/employee/task/update/{id}", method = RequestMethod.PUT)
    EmployeeTaskReadModel updateTask(@PathVariable(value = "id") Integer id, @RequestBody UpdateTaskResponse updateTaskRequest) {
        return service.updateTask(id, updateTaskRequest);
    }
}
