package cms.api.admin.payment;

import cms.domain.admin.service.PaymentCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PaymentCompanyController {
    private final PaymentCompanyService service;
    Logger logger = LoggerFactory.getLogger(PaymentCompanyController.class);

    public PaymentCompanyController(PaymentCompanyService service) {
        this.service = service;
    }

    @GetMapping
    @RequestMapping("/payment/companies")
    ResponseEntity<List<PaymentCompanyReadModel>> readAllCompanies() {
        return ResponseEntity.ok(service.readAll());
    }
}
