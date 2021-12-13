package cms.domain.admin.service;

import cms.api.admin.payment.PaymentCompanyReadModel;
import cms.domain.company.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentCompanyService {
    private final CompanyRepository repository;
    Logger logger = LoggerFactory.getLogger(CompanyService.class);

    public PaymentCompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<PaymentCompanyReadModel> readAll() {
        return repository.findAll().stream().map(PaymentCompanyReadModel::new).collect(Collectors.toList());
    }

}