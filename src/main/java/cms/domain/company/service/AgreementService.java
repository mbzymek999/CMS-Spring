package cms.domain.company.service;

import cms.api.company.request.AgreementRequest;
import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.entity.Agreement;
import cms.domain.company.entity.Company;
import cms.domain.company.repository.AgreementRepository;
import cms.domain.company.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AgreementService {

    private final AgreementRepository repository;
    private final CompanyRepository companyRepository;

    Logger logger = LoggerFactory.getLogger(AgreementService.class);

    public AgreementService(AgreementRepository repository, CompanyRepository companyRepository) {
        this.repository = repository;
        this.companyRepository = companyRepository;
    }

    public String addNewAgreement(AgreementRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();

        Company company = companyRepository.findById(userImpl.getId()).orElse(null);
        if(company == null){
            System.out.println("Firma nie istnieje");
        }

        Agreement agreement = request.getAgreement().toAgreement();

        agreement.setCompanyAgreement(company);

        repository.save(agreement);

        return "Ok";
    }
}