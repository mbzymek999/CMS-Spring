package cms.domain.admin.service;

import cms.api.admin.payment.PaymentAdminResponse;
import cms.api.admin.payment.PaymentReadModel;
import cms.api.admin.payment.PaymentWriteModel;
import cms.domain.admin.serviceImpl.PaymentAdminServiceImpl;
import cms.domain.company.entity.Company;
import cms.domain.company.entity.Payment;
import cms.domain.company.repository.CompanyRepository;
import cms.domain.company.repository.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentAdminService implements PaymentAdminServiceImpl {

    private final PaymentRepository paymentRepository;
    private final CompanyRepository companyRepository;

    public PaymentAdminService(PaymentRepository paymentRepository, CompanyRepository companyRepository) {
        this.paymentRepository = paymentRepository;
        this.companyRepository = companyRepository;
    }

    public String createPayment(Long companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        checkIfCompanyExist(company);
        assert company != null;
        Payment payment = new PaymentWriteModel().toPayment(company);
        payment.setCompany(company);
        paymentRepository.save(payment);

        return "Payment created.!";
    }

    public PaymentAdminResponse readAll(Pageable page) {
        List<PaymentReadModel> list = paymentRepository.findAll(page).stream().map(PaymentReadModel::new)
                .collect(Collectors.toList());
        Page<Payment> pageInformation = paymentRepository.findAll(page);
        return new PaymentAdminResponse(pageInformation, list);
    }

    public PaymentAdminResponse readByPaymentDone(Pageable page, boolean paymentDone) {
        List<PaymentReadModel> list = paymentRepository.findAllByPaymentDone(page, paymentDone).stream()
                .map(PaymentReadModel::new)
                .collect(Collectors.toList());
        Page<Payment> pageInformation = paymentRepository.findAllByPaymentDone(page, paymentDone);
        return new PaymentAdminResponse(pageInformation, list);
    }

    public PaymentAdminResponse readAllWithPaymentDone(Pageable page, Boolean paymentDone) {
        if (paymentDone == null) {
            return readAll(page);
        } else {
            return readByPaymentDone(page, paymentDone);
        }
    }

    public void checkIfCompanyExist(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("Firma nie istnieje");
        }
    }
}
