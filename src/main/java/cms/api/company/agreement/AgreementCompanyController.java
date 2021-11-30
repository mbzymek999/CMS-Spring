package cms.api.company.agreement;

import cms.domain.company.service.AgreementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
    public ResponseEntity<String> addNewAgreement(@RequestBody @Valid AgreementRequest request) {
        try {
            return ResponseEntity.ok(service.registerUser(request.validate()));
        }catch (NullPointerException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
