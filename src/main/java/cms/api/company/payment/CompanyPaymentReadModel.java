package cms.api.company.payment;

import cms.domain.company.entity.Payment;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyPaymentReadModel {
    private int paymentId;
    private Long companyId;
    private double price;
    private boolean paymentDone;
    private LocalDate datePayment;
    private LocalDate termPayment;


    public CompanyPaymentReadModel(Payment payment) {
        this.paymentId = payment.getId();
        this.companyId = payment.getCompany().getId();
        this.price = payment.getPrice();
        this.paymentDone = payment.isPaymentDone();
        this.datePayment = payment.getDatePayment();
        this.termPayment = payment.getTermPayment();
    }
}
