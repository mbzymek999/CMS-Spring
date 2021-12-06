package cms.api.company.task;

import cms.domain.company.service.TaskCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    List<TaskCompanyReadModel> readCompanyTasks(Pageable page) {
        logger.info("Reading company tasks");
        return service.readCompanyTasks(page);
    }

    @GetMapping("/all/task/company")
    public List<TaskDTO> getAllBrands(){
        return service.getAllTasks().stream().map(TaskConverter::toDTO).collect(Collectors.toList());
    }

}
