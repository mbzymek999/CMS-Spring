package cms.domain.company.service;

import cms.api.company.dto.CompanyReadModel;
import cms.api.company.dto.PaymentReadModel;
import cms.api.company.dto.PaymentWriteModel;
import cms.api.company.request.PaymentRequest;
import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.entity.Company;
import cms.domain.company.entity.Payment;
import cms.domain.company.repository.CompanyRepository;
import cms.domain.company.repository.PaymentRepository;
import cms.domain.employee.entity.Employee;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CompanyRepository companyRepository;

    public PaymentService(PaymentRepository paymentRepository, CompanyRepository companyRepository) {
        this.paymentRepository = paymentRepository;
        this.companyRepository = companyRepository;
    }

    public String createPayment(Long companyId) {

        Company company = companyRepository.findById(companyId).orElse(null);
        if(company == null){
            System.out.println("Firma nie istnieje");
        }

        // Create payment
        Payment payment = new PaymentWriteModel().toPayment(company);

        payment.setCompany(company);
        paymentRepository.save(payment);

        return "Payment created.!";
    }

    public List<PaymentReadModel> readAll() {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();

        return paymentRepository.findAll().stream().map(PaymentReadModel::new).collect(Collectors.toList());
//        return paymentRepository.findAllCompanies(userImpl.getId()).stream().map(PaymentReadModel::new).collect(Collectors.toList());

    }
}
