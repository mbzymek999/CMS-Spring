package cms.api.admin.register.company;

import cms.domain.admin.service.RegisterCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> addNewCompany(@RequestBody SignupCompanyRequest request) {
        try {
            return ResponseEntity.ok(service.registerUser(request));
        }catch (NullPointerException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
