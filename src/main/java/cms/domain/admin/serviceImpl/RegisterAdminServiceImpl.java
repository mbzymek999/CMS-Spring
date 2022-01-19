package cms.domain.admin.serviceImpl;

import cms.api.admin.register.admin.SignupAdminRequest;

public interface RegisterAdminServiceImpl {

    String registerAdmin(SignupAdminRequest signUpRequest);

    void checkIfUserNameAlreadyExist(SignupAdminRequest signUpRequest);

    void checkIfEmailAlreadyExist(SignupAdminRequest signUpRequest);
}
