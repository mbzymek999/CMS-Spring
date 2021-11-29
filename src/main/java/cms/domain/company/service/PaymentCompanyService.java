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
public class PaymentCompanyService {

    private final PaymentRepository paymentRepository;
    public PaymentCompanyService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<CompanyPaymentReadModel> readCompanyPayments() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();

        return paymentRepository.findAllByCompany_User_Id(userImpl.getId()).stream().map(CompanyPaymentReadModel::new).collect(Collectors.toList());
    }

}
