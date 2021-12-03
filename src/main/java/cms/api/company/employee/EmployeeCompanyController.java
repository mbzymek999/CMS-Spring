package cms.api.company.employee;

import cms.api.company.agreement.AgreementCompanyReadModel;
import cms.domain.company.service.AgreementService;
import cms.domain.company.service.EmployeeCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<EmployeeCompanyReadModel>> readCompanyEmployees() {
        try {
            logger.info("Reading company employees");
            return ResponseEntity.ok(service.readCompanyEmployees());
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}