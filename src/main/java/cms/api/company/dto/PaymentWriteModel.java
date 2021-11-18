package cms.api.company.dto;

import cms.domain.company.entity.Company;
import cms.domain.company.entity.Payment;

import java.time.LocalDate;

public class PaymentWriteModel {

    public Payment toPayment(Company company){
        Payment payment = new Payment();
        payment.setPrice(company.getMaxEmployees() * 10);
        payment.setDatePayment(LocalDate.now());
        payment.setPaymentDone(false);
        return payment;
    }
}
