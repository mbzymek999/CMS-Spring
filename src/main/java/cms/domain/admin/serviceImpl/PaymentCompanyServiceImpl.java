package cms.domain.admin.serviceImpl;

import cms.api.admin.payment.PaymentCompanyReadModel;

import java.util.List;

public interface PaymentCompanyServiceImpl {

    List<PaymentCompanyReadModel> readAll();
}
