package cms.api.admin.register.company;

import cms.domain.admin.service.RegisterCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class RegisterCompanyController {

    private final RegisterCompanyService service;
    Logger logger = LoggerFactory.getLogger(RegisterCompanyController.class);

    public RegisterCompanyController(RegisterCompanyService service) {
        this.service = service;
    }

    @PostMapping("/signup/company")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewCompany(@Valid @RequestBody SignupCompanyRequest request) {
        return service.registerUser(request);
    }

}
