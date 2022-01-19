package cms.api.user;

import cms.config.security.services.UserDetailsImpl;
import cms.domain.user.serviceImpl.UserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PreAuthorize("hasAnyRole('COMPANY','EMPLOYEE')")
    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    String updateUser(@AuthenticationPrincipal UserDetailsImpl userDetails,
                      @RequestBody UserRequest userRequest) {
        return userServiceImpl.updateUser(userDetails.getIdClient(), userRequest);
    }
}
