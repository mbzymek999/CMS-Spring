package cms.domain.company.service;

import cms.api.company.agreement.AgreementCompanyReadModel;
import cms.api.company.employee.EmployeeCompanyReadModel;
import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.repository.AgreementRepository;
import cms.domain.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeCompanyService {

    private final AgreementRepository agreementRepository;

    public EmployeeCompanyService(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }

    public List<EmployeeCompanyReadModel> readCompanyEmployees() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();
        return agreementRepository.findAllByCompanyAgreement_User_Id(userImpl.getId()).stream().map(EmployeeCompanyReadModel::new).collect(Collectors.toList());
    }
}
