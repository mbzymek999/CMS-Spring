package cms.api.admin.controller;

import cms.api.admin.request.SignupAgreementTestRequest;
import cms.api.admin.request.SignupCompanyRequest;
import cms.config.security.payload.response.MessageResponse;
import cms.domain.company.entity.AgreementTest;
import cms.domain.company.entity.Company;
import cms.domain.company.repository.AgreementTestRepository;
import cms.domain.company.repository.CompanyRepository;
import cms.domain.user.entity.ERole;
import cms.domain.user.entity.Role;
import cms.domain.user.entity.User;
import cms.domain.user.repository.RoleRepository;
import cms.domain.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AgreementTestController {

    private final UserRepository userRepository;

    private final CompanyRepository companyRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final AgreementTestRepository agreementTestRepository;

    public AgreementTestController(UserRepository userRepository, CompanyRepository companyRepository, RoleRepository roleRepository, PasswordEncoder encoder, AgreementTestRepository agreementTestRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.agreementTestRepository = agreementTestRepository;
    }

    @PostMapping("/signup/test")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupAgreementTestRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Nazwa użytkownika zajęta"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email jest w zajęty"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        // Create new company
        Company company = new Company(
                signUpRequest.getCompanyName(),
                user);

        // Create new AgreementTest
        AgreementTest agreementTest = new AgreementTest(
                signUpRequest.getName(),
                signUpRequest.getAssignedDate(),
                user,
                company
        );

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_COMPANY)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if ("company".equals(role)) {
                    Role companyRole = roleRepository.findByName(ERole.ROLE_COMPANY)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(companyRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        companyRepository.save(company);
        agreementTestRepository.save(agreementTest);

        return ResponseEntity.ok(new MessageResponse("Company registered successfully!"));
    }
}
