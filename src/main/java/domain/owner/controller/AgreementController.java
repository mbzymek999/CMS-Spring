package domain.owner.controller;

import domain.owner.request.AgreementRequest;
import domain.owner.service.AgreementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgreementController {
    @Autowired
    private final AgreementService service;
    Logger logger = LoggerFactory.getLogger(AgreementController.class);

    public AgreementController(AgreementService service) {
        this.service = service;
    }

    @PostMapping("/agreement/create")
    @PreAuthorize("hasRole('OWNER')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addNewAgreement(@RequestBody AgreementRequest request) {
        try {
            return ResponseEntity.ok(service.addNewAgreement(request));
        }catch (NullPointerException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
