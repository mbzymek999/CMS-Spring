package cms.api.employee.task;

import cms.domain.employee.service.EmployeeTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

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
    ResponseEntity<List<EmployeeTaskReadModel>> readCompanyTasks(@PathVariable int statusTask) {
        try {
            logger.info("Reading employee tasks");
            return ResponseEntity.ok(service.readEmployeeTasks(statusTask));
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @Transactional
    @RequestMapping(value = "/employee/task/update/{id}", method = RequestMethod.PUT)
    ResponseEntity<EmployeeTaskReadModel> updateTask(@PathVariable(value = "id") Integer id, @RequestBody UpdateTaskRequest updateTaskRequest) {
        try {
            logger.info("Updating task");
            return ResponseEntity.ok(service.updateTask(id, updateTaskRequest));
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}
