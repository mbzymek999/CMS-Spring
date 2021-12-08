package cms.api.company.task;

import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.service.TaskCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
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
                                             @RequestParam(value = "employeeId") Long employeeId,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            return ResponseEntity.ok(service.addNewTask(request, employeeId, userDetails.getId()));
        } catch (NullPointerException e) { // Todo wyzrzucic null pointer exception
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @RequestMapping("/company/tasks")
    ReadCompanyTasksResponse readCompanyTasks(Pageable page, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        logger.info("Reading company tasks");
        return service.readCompanyTasks(page, userDetails.getId());
    }

    @GetMapping("/all/task/company")
    public List<TaskDTO> getAllTasks(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.getAllTasks(userDetails.getId()).stream().map(TaskConverter::toDTO).collect(Collectors.toList());
    }

}
