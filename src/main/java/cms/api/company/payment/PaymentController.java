package cms.api.company.payment;

import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.serviceImpl.CompanyPaymentServiceImpl;
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


    private final CompanyPaymentServiceImpl companyPaymentServiceImpl;

    public PaymentController(CompanyPaymentServiceImpl companyPaymentServiceImpl) {
        this.companyPaymentServiceImpl = companyPaymentServiceImpl;
    }

    @GetMapping
    @RequestMapping("/company/payments")
    @PreAuthorize("hasRole('COMPANY')")
    List<CompanyPaymentReadModel> readCompanyPayments(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return companyPaymentServiceImpl.readCompanyPayments(userDetails.getIdClient());
    }
}