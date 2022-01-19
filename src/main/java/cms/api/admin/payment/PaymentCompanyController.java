package cms.api.admin.payment;

import cms.domain.admin.serviceImpl.PaymentCompanyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PaymentCompanyController {

    private final PaymentCompanyServiceImpl paymentCompanyServiceImpl;

    public PaymentCompanyController(PaymentCompanyServiceImpl paymentCompanyServiceImpl) {
        this.paymentCompanyServiceImpl = paymentCompanyServiceImpl;
    }

    @GetMapping
    @RequestMapping("/payment/companies")
    ResponseEntity<List<PaymentCompanyReadModel>> readAllCompanies() {
        return ResponseEntity.ok(paymentCompanyServiceImpl.readAll());
    }
}
