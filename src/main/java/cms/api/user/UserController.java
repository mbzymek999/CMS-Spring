package cms.api.user;

import cms.config.security.services.UserDetailsImpl;
import cms.domain.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('COMPANY','EMPLOYEE')")
    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    String updateUser(@AuthenticationPrincipal UserDetailsImpl userDetails,
                      @RequestBody UserRequest userRequest) {
        return service.updateUser(userDetails.getIdClient(), userRequest);
    }
}
