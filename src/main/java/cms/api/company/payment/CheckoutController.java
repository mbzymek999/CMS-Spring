package cms.api.company.payment;

import cms.domain.company.entity.Payment;
import cms.domain.company.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckoutController {

    Logger logger = LoggerFactory.getLogger(CheckoutController.class);
    PaymentRepository paymentRepository;

    public CheckoutController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @PostMapping("/checkout")
    public String checkout(@RequestParam (value = "idPayment") String idPayment, Model model) {
        Payment payment = paymentRepository.findById(Integer.valueOf(idPayment)).orElseThrow();
        model.addAttribute("paymentId", payment.getId());
        model.addAttribute("amount", (int) payment.getPrice() * 100);
        model.addAttribute("datePayment", payment.getDatePayment());
        model.addAttribute("termPayment", payment.getTermPayment());
        model.addAttribute("companyName", payment.getCompany().getCompanyName());
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.PLN);
        return "checkout";
    }
}