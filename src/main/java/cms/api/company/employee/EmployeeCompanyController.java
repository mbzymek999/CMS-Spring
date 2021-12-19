package cms.api.company.employee;

import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.service.EmployeeCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmployeeCompanyController {

    private final EmployeeCompanyService service;
    Logger logger = LoggerFactory.getLogger(EmployeeCompanyController.class);
    public EmployeeCompanyController(EmployeeCompanyService service) {
        this.service = service;
    }

    @GetMapping
    @RequestMapping("/company/employees")
    @PreAuthorize("hasRole('COMPANY')")
    ResponseEntity<List<EmployeeCompanyReadModel>> readCompanyEmployees(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            logger.info("Reading company employees");
            return ResponseEntity.ok(service.readCompanyEmployees(userDetails.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @RequestMapping(value = "/company/employee/update/{id}", method = RequestMethod.PUT)
    EmployeeCompanyReadModel updateEmployee(@PathVariable(value = "id") Long id, @RequestBody UpdateEmployeeResponse updateEmployeeResponse) {
        return service.updateEmployee(id, updateEmployeeResponse);
    }
}
