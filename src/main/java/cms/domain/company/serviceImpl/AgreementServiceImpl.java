package cms.domain.company.serviceImpl;

import cms.api.company.agreement.AgreementCompanyReadModel;
import cms.api.company.agreement.AgreementDetailCompanyReadModel;
import cms.api.company.agreement.AgreementRequest;
import cms.domain.company.entity.Company;

import java.util.List;

public interface AgreementServiceImpl {

    String registerUser(AgreementRequest signUpRequest, String idClient);

    List<AgreementCompanyReadModel> readCompanyAgreements(String idClient);

    AgreementDetailCompanyReadModel readCompanyDetails(String idClient, int agreementId);

    void sendEmail(AgreementRequest signUpRequest);

    void checkIfUserNameAlreadyExist(AgreementRequest signUpRequest);

    void checkIfEmailAlreadyExist(AgreementRequest signUpRequest);

    void checkIfCompanyExist(Company company);

}
