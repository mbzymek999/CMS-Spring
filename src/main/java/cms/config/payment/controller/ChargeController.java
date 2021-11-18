package cms.config.payment.controller;

import cms.config.payment.request.ChargeRequest;
import cms.config.payment.service.StripeService;
import cms.domain.company.entity.Payment;
import cms.domain.company.repository.PaymentRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChargeController {

    PaymentRepository paymentRepository;
    private StripeService paymentsService;

    public ChargeController(PaymentRepository paymentRepository, StripeService paymentsService) {
        this.paymentRepository = paymentRepository;
        this.paymentsService = paymentsService;
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