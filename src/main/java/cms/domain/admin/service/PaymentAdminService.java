package cms.domain.admin.service;

import cms.api.admin.payment.PaymentReadModel;
import cms.api.admin.payment.PaymentWriteModel;
import cms.domain.company.entity.Company;
import cms.domain.company.entity.Payment;
import cms.domain.company.repository.CompanyRepository;
import cms.domain.company.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentAdminService {

    private final PaymentRepository paymentRepository;
    private final CompanyRepository companyRepository;
    public PaymentAdminService(PaymentRepository paymentRepository, CompanyRepository companyRepository) {
        this.paymentRepository = paymentRepository;
        this.companyRepository = companyRepository;
    }

    public String createPayment(Long companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);

        checkIfCompanyExist(company);

        // Create payment
        Payment payment = new PaymentWriteModel().toPayment(company);
        payment.setCompany(company);
        paymentRepository.save(payment);

        return "Payment created.!";
    }

    public List<PaymentReadModel> readAll() {
        return paymentRepository.findAll().stream().map(PaymentReadModel::new).collect(Collectors.toList());
    }

    public void checkIfCompanyExist(Company company) {
        if(company == null){
            throw new IllegalArgumentException("Firma nie istnieje");
        }
    }
}
