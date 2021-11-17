package cms.api.company.controller;

import cms.api.company.request.PaymentRequest;
import cms.domain.company.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    private final PaymentService service;
    Logger logger = LoggerFactory.getLogger(cms.api.company.controller.PaymentController.class);
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/api/payment")
//    @PreAuthorize("hasRole('COMPANY')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createPayment(@RequestBody PaymentRequest request,
                                                @RequestParam(value = "companyId") Long companyId) {
        try {
            return ResponseEntity.ok(service.createPayment(request, companyId));
        }catch (NullPointerException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}