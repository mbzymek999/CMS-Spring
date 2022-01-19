package cms.domain.company.serviceImpl;

import cms.api.company.payment.CompanyPaymentReadModel;

import java.util.List;

public interface CompanyPaymentServiceImpl {

    List<CompanyPaymentReadModel> readCompanyPayments(String idClient);

}
