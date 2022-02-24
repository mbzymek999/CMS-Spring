package cms.domain.company.serviceImpl;

import cms.api.company.agreement.AgreementCompanyReadModel;
import cms.api.company.agreement.AgreementDetailCompanyReadModel;
import cms.api.company.agreement.AgreementRequest;

import java.util.List;

public interface AgreementServiceImpl {

    String registerUser(AgreementRequest signUpRequest, String idClient);

    List<AgreementCompanyReadModel> readCompanyAgreements(String idClient);

    AgreementDetailCompanyReadModel readCompanyDetails(String idClient, int agreementId);

}
