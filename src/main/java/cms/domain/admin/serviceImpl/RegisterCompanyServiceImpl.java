package cms.domain.admin.serviceImpl;

import cms.api.admin.register.company.SignupCompanyRequest;

public interface RegisterCompanyServiceImpl {

    String registerUser(SignupCompanyRequest signUpRequest);

    void checkIfUserNameAlreadyExist(SignupCompanyRequest signUpRequest);

    void checkIfEmailAlreadyExist(SignupCompanyRequest signUpRequest);
}
