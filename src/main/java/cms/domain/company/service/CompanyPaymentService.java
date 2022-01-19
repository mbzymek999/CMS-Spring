package cms.domain.company.service;

import cms.api.company.payment.CompanyPaymentReadModel;
import cms.domain.company.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyPaymentService {

    private final PaymentRepository paymentRepository;

    public CompanyPaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<CompanyPaymentReadModel> readCompanyPayments(String idClient) {
        return paymentRepository.findAllByCompany_User_IdClient(idClient).stream().map(CompanyPaymentReadModel::new).collect(Collectors.toList());
    }

}
