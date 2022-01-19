package cms.domain.admin.serviceImpl;

import cms.api.admin.payment.PaymentAdminResponse;
import cms.domain.company.entity.Company;
import org.springframework.data.domain.Pageable;

public interface PaymentAdminServiceImpl {

    String createPayment(Long companyId);

    PaymentAdminResponse readAll(Pageable page);

    PaymentAdminResponse readByPaymentDone(Pageable page, boolean paymentDone);

    PaymentAdminResponse readAllWithPaymentDone(Pageable page, Boolean paymentDone);

    void checkIfCompanyExist(Company company);
}
