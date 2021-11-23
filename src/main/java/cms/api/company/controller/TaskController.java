package cms.api.company.controller;

import cms.api.company.dto.CompanyTaskReadModel;
import cms.api.company.dto.TaskReadModel;
import cms.api.company.request.TaskRequest;
import cms.domain.company.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService service;
    Logger logger = LoggerFactory.getLogger(TaskController.class);
    public TaskController(TaskService service) {
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
    @RequestMapping("/tasks")
    ResponseEntity<List<TaskReadModel>> readAllTasks() {
        return ResponseEntity.ok(service.readAll());
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
