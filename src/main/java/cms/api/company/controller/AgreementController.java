package cms.api.company.controller;

import cms.api.company.request.AgreementRequest;
import cms.domain.company.service.AgreementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AgreementController {
    private final AgreementService service;
    Logger logger = LoggerFactory.getLogger(AgreementController.class);
    public AgreementController(AgreementService service) {
        this.service = service;
    }

    @PostMapping("/create/agreement")
//    @PreAuthorize("hasRole('COMPANY')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addNewAgreement(@RequestBody AgreementRequest request) {
        try {
            return ResponseEntity.ok(service.registerUser(request));
        }catch (NullPointerException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
