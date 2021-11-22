package cms.api.company.dto;

import cms.domain.company.entity.Payment;
import lombok.Data;


@Data
public class PaymentReadModel {
    private int paymentId;
    private double price;
    private boolean paymentDone;
    private String companyName;

    public PaymentReadModel(Payment payment){
        this.paymentId = payment.getId();
        this.price = payment.getPrice();
        this.paymentDone = payment.isPaymentDone();
        this.companyName = payment.getCompany().getCompanyName();
    }
}
