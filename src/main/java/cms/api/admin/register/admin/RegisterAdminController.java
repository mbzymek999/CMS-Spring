package cms.api.admin.register.admin;

import cms.domain.admin.service.RegisterAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class RegisterAdminController {

    private final RegisterAdminService service;
    Logger logger = LoggerFactory.getLogger(RegisterAdminController.class);
    public RegisterAdminController(RegisterAdminService service) {
        this.service = service;
    }

    @PostMapping("/signup/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addNewAdmin(@RequestBody @Valid SignupAdminRequest request) {
        try {
            return ResponseEntity.ok(service.registerAdmin(request));
        }catch (NullPointerException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
