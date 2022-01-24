package cms.api.company.payment;

import cms.domain.company.entity.Payment;
import cms.domain.company.repository.PaymentRepository;
import cms.domain.user.repository.UserRepository;
import cms.external.payment.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChargeController {

    PaymentRepository paymentRepository;
    private final StripeService paymentsService;
    private final UserRepository userRepository;

    public ChargeController(PaymentRepository paymentRepository, StripeService paymentsService, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentsService = paymentsService;
        this.userRepository = userRepository;
    }

    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model) throws StripeException {
        Payment payment = paymentRepository.findById(chargeRequest.getPaymentId()).orElseThrow();
        if(payment.isPaymentDone())
            throw new IllegalStateException("Pakiet został opłacony");

        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.PLN);
        Charge charge = paymentsService.charge(chargeRequest);
        payment.setPaymentDone(true);
        paymentRepository.save(payment);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
    @ExceptionHandler(IllegalStateException.class)
    public String handlePaymentException(Model model, IllegalStateException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}