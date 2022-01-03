package cms.api.admin.payment;

import cms.api.company.payment.PaymentController;
import cms.domain.admin.service.PaymentAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PaymentAdminController {

    private final PaymentAdminService service;
    Logger logger = LoggerFactory.getLogger(PaymentController.class);
    public PaymentAdminController(PaymentAdminService service) {
        this.service = service;
    }

    @PostMapping("/api/payment")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public String createPayment(@RequestParam(value = "companyId") Long companyId) {
        return service.createPayment(companyId);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/payments/read")
    PaymentAdminResponse readAllPayments(Pageable page,
                                         @Param("paymentDone") Boolean paymentDone) {
        return service.readAllWithPaymentDone(page, paymentDone);
    }
}
