package cms.api.company.task;

import cms.api.company.dto.CompanyTaskReadModel;
import cms.domain.company.service.TaskCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskCompanyController {

    private final TaskCompanyService service;
    Logger logger = LoggerFactory.getLogger(TaskCompanyController.class);
    public TaskCompanyController(TaskCompanyService service) {
        this.service = service;
    }

    @PostMapping("/task/add")
    @PreAuthorize("hasRole('COMPANY')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addNewTask(@RequestBody TaskRequest request,
                                             @RequestParam(value = "employeeId") Long employeeId) {
        try {
            return ResponseEntity.ok(service.addNewTask(request, employeeId));
        }catch (NullPointerException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @RequestMapping("/company/tasks")
    ResponseEntity<List<CompanyTaskReadModel>> readCompanyTasks() {
        try {
            logger.info("Reading company tasks");
            return ResponseEntity.ok(service.readCompanyTasks());
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

}
