package cms.api.admin.register.admin;

import cms.domain.admin.serviceImpl.RegisterAdminServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class RegisterAdminController {

    private final RegisterAdminServiceImpl registerAdminServiceImpl;

    public RegisterAdminController(RegisterAdminServiceImpl registerAdminServiceImpl) {
        this.registerAdminServiceImpl = registerAdminServiceImpl;
    }

    @PostMapping("/signup/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addNewAdmin(@RequestBody @Valid SignupAdminRequest request) {
        try {
            return ResponseEntity.ok(registerAdminServiceImpl.registerAdmin(request));
        } catch (NullPointerException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
