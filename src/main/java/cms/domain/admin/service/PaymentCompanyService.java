package cms.domain.admin.service;

import cms.api.admin.payment.PaymentCompanyReadModel;
import cms.domain.admin.serviceImpl.PaymentCompanyServiceImpl;
import cms.domain.company.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentCompanyService implements PaymentCompanyServiceImpl {

    private final CompanyRepository repository;

    public PaymentCompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<PaymentCompanyReadModel> readAll() {
        return repository.findAll().stream().map(PaymentCompanyReadModel::new).collect(Collectors.toList());
    }

}