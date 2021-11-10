package cms.api.company.controller;

import cms.api.company.request.TaskRequest;
import cms.domain.company.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
