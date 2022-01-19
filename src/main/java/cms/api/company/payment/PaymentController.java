package cms.api.company.payment;

import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.serviceImpl.CompanyPaymentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PaymentController {

    Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final CompanyPaymentServiceImpl companyPaymentServiceImpl;

    public PaymentController(CompanyPaymentServiceImpl companyPaymentServiceImpl) {
        this.companyPaymentServiceImpl = companyPaymentServiceImpl;
    }

    @GetMapping
    @RequestMapping("/company/payments")
    @PreAuthorize("hasRole('COMPANY')")
    ResponseEntity<List<CompanyPaymentReadModel>> readCompanyPayments(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            logger.info("Reading company payments");
            return ResponseEntity.ok(companyPaymentServiceImpl.readCompanyPayments(userDetails.getIdClient()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}