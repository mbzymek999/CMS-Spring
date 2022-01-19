package cms.api.admin.payment;

import cms.domain.admin.serviceImpl.PaymentAdminServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PaymentAdminController {

    private final PaymentAdminServiceImpl paymentAdminServiceImpl;

    public PaymentAdminController(PaymentAdminServiceImpl paymentAdminServiceImpl) {
        this.paymentAdminServiceImpl = paymentAdminServiceImpl;
    }

    @PostMapping("/api/payment")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public String createPayment(@RequestParam(value = "companyId") Long companyId) {
        return paymentAdminServiceImpl.createPayment(companyId);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/payments/read")
    PaymentAdminResponse readAllPayments(Pageable page,
                                         @Param("paymentDone") Boolean paymentDone) {
        return paymentAdminServiceImpl.readAllWithPaymentDone(page, paymentDone);
    }
}
