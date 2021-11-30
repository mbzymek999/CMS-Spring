package cms.domain.admin.service;

import cms.api.admin.users.company.CompanyReadModel;
import cms.domain.company.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository repository;
    Logger logger = LoggerFactory.getLogger(CompanyService.class);

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<CompanyReadModel> readAll() {
        return repository.findAll().stream().map(CompanyReadModel::new).collect(Collectors.toList());
    }
}
