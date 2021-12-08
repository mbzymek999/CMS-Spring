package cms.api.company.agreement;

import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.service.AgreementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AgreementCompanyController {
    private final AgreementService service;
    Logger logger = LoggerFactory.getLogger(AgreementCompanyController.class);
    public AgreementCompanyController(AgreementService service) {
        this.service = service;
    }

    @PostMapping("/create/agreement")
    @PreAuthorize("hasRole('COMPANY')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addNewAgreement(@RequestBody @Valid AgreementRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
            return ResponseEntity.ok(service.registerUser(request.validate(),userDetails.getId()));
    }

    @GetMapping
    @RequestMapping("/company/agreements")
    @PreAuthorize("hasRole('COMPANY')")
    ResponseEntity<List<AgreementCompanyReadModel>> readCompanyAgreements(@AuthenticationPrincipal UserDetailsImpl userDetails) {
            logger.info("Reading company agreements");
            return ResponseEntity.ok(service.readCompanyAgreements(userDetails.getId()));
    }

    @GetMapping
    @RequestMapping("/company/agreement/{id}")
    @PreAuthorize("hasRole('COMPANY')")
    ResponseEntity<AgreementDetailCompanyReadModel> readCompanyAgreementDetails(@PathVariable int id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            logger.info("reading agreement with id: " + id);
            return ResponseEntity.ok(service.readCompanyDetails(userDetails.getId(), id));
        }catch (NullPointerException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
