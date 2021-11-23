package cms.api.company.dto;

import cms.domain.company.entity.Payment;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyPaymentReadModel {
    private Long companyId;
    private double price;
    private boolean paymentDone;
    private LocalDate datePayment;


    public CompanyPaymentReadModel(Payment payment) {
        this.companyId = payment.getCompany().getId();
        this.price = payment.getPrice();
        this.paymentDone = payment.isPaymentDone();
        this.datePayment = payment.getDatePayment();
    }
}
