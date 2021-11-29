package cms.api.admin.payment;

import cms.domain.company.entity.Company;
import cms.domain.company.entity.Payment;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class PaymentWriteModel {

    LocalDate now = LocalDate.now();
    LocalDate lastDay = now.with(TemporalAdjusters.lastDayOfMonth());

    public Payment toPayment(Company company){
        Payment payment = new Payment();
        payment.setPrice(company.getMaxEmployees() * 10);
        payment.setDatePayment(LocalDate.now());
        payment.setTermPayment(lastDay);
        payment.setPaymentDone(false);
        return payment;
    }
}
