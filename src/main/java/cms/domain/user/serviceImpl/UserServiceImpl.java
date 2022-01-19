package cms.domain.user.serviceImpl;

import cms.api.user.UserRequest;

public interface UserServiceImpl {

    String updateUser(String idClient, UserRequest userRequest);
}
