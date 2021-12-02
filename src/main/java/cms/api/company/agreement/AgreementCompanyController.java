package cms.api.company.agreement;

import cms.domain.company.service.AgreementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AgreementCompanyController {
    private final AgreementService service;
    Logger logger = LoggerFactory.getLogger(AgreementCompanyController.class);
    public AgreementCompanyController(AgreementService service) {
        this.service = service;
    }

    @PostMapping("/create/agreement")
//    @PreAuthorize("hasRole('COMPANY')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addNewAgreement(@RequestBody @Valid AgreementRequest request) throws Exception {
            return ResponseEntity.ok(service.registerUser(request.validate()));
    }

    @GetMapping
    @RequestMapping("/company/agreements")
    ResponseEntity<List<AgreementCompanyReadModel>> readCompanyAgreements() {
        try {
            logger.info("Reading company agreements");
            return ResponseEntity.ok(service.readCompanyAgreements());
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}
