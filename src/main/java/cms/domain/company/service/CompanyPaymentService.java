package cms.domain.company.service;

import cms.api.company.payment.CompanyPaymentReadModel;
import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.repository.PaymentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyPaymentService {

    private final PaymentRepository paymentRepository;
    public CompanyPaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<CompanyPaymentReadModel> readCompanyPayments(Long id) {
        return paymentRepository.findAllByCompany_User_Id(id).stream().map(CompanyPaymentReadModel::new).collect(Collectors.toList());
    }

}
