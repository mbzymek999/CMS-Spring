package cms.api.company.task;

import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.service.TaskCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public String addNewTask(@RequestBody TaskRequest request,
                             @RequestParam(value = "employeeId") Long employeeId,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return service.addNewTask(request, employeeId, userDetails.getId());
    }

    @GetMapping
    @RequestMapping("/company/tasks")
    ReadCompanyTasksResponse readCompanyTasks(Pageable page, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        logger.info("Reading company tasks");
        return service.readCompanyTasks(page, userDetails.getId());
    }

    @GetMapping
    @RequestMapping("/company/tasks/read")
    ReadCompanyTasksResponse readCompanyTasksWithStatusTaskFilter(Pageable page,
                                                                  @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                  @Param("statusTask") int statusTask) {
        logger.info("Reading company tasks");
        return service.readAllWithStatusTask(page, userDetails.getId(), statusTask);
    }

}
