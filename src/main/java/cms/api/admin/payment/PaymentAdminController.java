package cms.api.admin.payment;

import cms.api.company.payment.PaymentController;
import cms.domain.admin.service.PaymentAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PaymentAdminController {

    private final PaymentAdminService service;
    Logger logger = LoggerFactory.getLogger(PaymentController.class);
    public PaymentAdminController(PaymentAdminService service) {
        this.service = service;
    }

    @PostMapping("/api/payment")
//    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createPayment(@RequestParam(value = "companyId") Long companyId) {
        try {
            return ResponseEntity.ok(service.createPayment(companyId));
        }catch (NullPointerException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/payments")
    ResponseEntity<List<PaymentReadModel>> readAllPayments() {
        return ResponseEntity.ok(service.readAll());
    }
}
