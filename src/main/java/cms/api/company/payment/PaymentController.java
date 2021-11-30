package cms.api.company.payment;

import cms.domain.company.service.CompanyPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PaymentController {
    private final CompanyPaymentService service;
    Logger logger = LoggerFactory.getLogger(PaymentController.class);
    public PaymentController(CompanyPaymentService service) {
        this.service = service;
    }

    @GetMapping
    @RequestMapping("/company/payments")
    ResponseEntity<List<CompanyPaymentReadModel>> readCompanyPayments() {
        try {
            logger.info("Reading company payments");
            return ResponseEntity.ok(service.readCompanyPayments());
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}