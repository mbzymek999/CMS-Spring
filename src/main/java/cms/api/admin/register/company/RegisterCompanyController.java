package cms.api.admin.register.company;

import cms.domain.admin.serviceImpl.RegisterCompanyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class RegisterCompanyController {

    private final RegisterCompanyServiceImpl registerCompanyServiceImpl;

    public RegisterCompanyController(RegisterCompanyServiceImpl registerCompanyServiceImpl) {
        this.registerCompanyServiceImpl = registerCompanyServiceImpl;
    }

    @PostMapping("/signup/company")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewCompany(@Valid @RequestBody SignupCompanyRequest request) {
        return registerCompanyServiceImpl.registerUser(request);
    }

}
