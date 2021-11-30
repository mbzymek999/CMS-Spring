package cms.api.admin.users.company;

import cms.domain.admin.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {
    private final CompanyService service;
    Logger logger = LoggerFactory.getLogger(CompanyController.class);
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    @RequestMapping("/companies")
    ResponseEntity<List<CompanyReadModel>> readAllCompanies() {
        return ResponseEntity.ok(service.readAll());
    }
}
