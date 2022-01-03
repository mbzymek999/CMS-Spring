package cms.domain.admin.service;

import cms.api.admin.register.company.SignupCompanyRequest;
import cms.domain.company.entity.Company;
import cms.domain.company.repository.CompanyRepository;
import cms.domain.user.entity.ERole;
import cms.domain.user.entity.Role;
import cms.domain.user.entity.User;
import cms.domain.user.repository.RoleRepository;
import cms.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class RegisterCompanyService {
    private final UserRepository userRepository;

    private final CompanyRepository companyRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    public RegisterCompanyService(UserRepository userRepository, CompanyRepository companyRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public String registerUser(SignupCompanyRequest signUpRequest) {

        checkIfUserNameAlreadyExist(signUpRequest);
        checkIfEmailAlreadyExist(signUpRequest);

        String randomId = UUID.randomUUID().toString().replace("-", "");

        // Create new user's account
        User user = new User(
                randomId,
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        // Create new company
        Company company = new Company(
                signUpRequest.getCompanyName(),
                signUpRequest.getShortCompanyName(),
                signUpRequest.getNip(),
                signUpRequest.getRegon(),
                signUpRequest.getRepresentativePerson(),
                signUpRequest.getPhone(),
                signUpRequest.getStreet(),
                signUpRequest.getStreetNumber(),
                signUpRequest.getBuildingNumber(),
                signUpRequest.getCity(),
                signUpRequest.getPostcode(),
                signUpRequest.getProvince(),
                signUpRequest.getCountry(),
                signUpRequest.getAdditionalFields(),
                signUpRequest.getMaxEmployees(),
                user);

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

        return "Company registered successfully!";
    }

    private void checkIfUserNameAlreadyExist(SignupCompanyRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new IllegalArgumentException("Error: Nazwa użytkownika zajęta");
        }
    }

    public void checkIfEmailAlreadyExist(SignupCompanyRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new IllegalArgumentException("Error: Email jest już zajęty");
        }
    }
}
