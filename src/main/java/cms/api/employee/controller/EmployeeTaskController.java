package cms.api.employee.controller;

import cms.api.employee.dto.EmployeeTaskReadModel;
import cms.domain.employee.service.EmployeeTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping("/employee/tasks")
    ResponseEntity<List<EmployeeTaskReadModel>> readCompanyTasks() {
        try {
            logger.info("Reading employee tasks");
            return ResponseEntity.ok(service.readEmployeeTasks());
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}
