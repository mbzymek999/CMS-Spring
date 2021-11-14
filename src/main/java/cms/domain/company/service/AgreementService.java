package cms.domain.company.service;

import cms.api.company.request.AgreementRequest;
import cms.config.security.services.UserDetailsImpl;
import cms.domain.company.entity.Agreement;
import cms.domain.company.entity.Company;
import cms.domain.company.repository.AgreementRepository;
import cms.domain.company.repository.CompanyRepository;
import cms.domain.employee.entity.Employee;
import cms.domain.employee.repository.EmployeeRepository;
import cms.domain.user.entity.ERole;
import cms.domain.user.entity.Role;
import cms.domain.user.entity.User;
import cms.domain.user.repository.RoleRepository;
import cms.domain.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class AgreementService {

    private final UserRepository userRepository;

    private final CompanyRepository companyRepository;

    private final EmployeeRepository employeeRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final AgreementRepository agreementRepository;

    public AgreementService(UserRepository userRepository, CompanyRepository companyRepository, EmployeeRepository employeeRepository, RoleRepository roleRepository, PasswordEncoder encoder, AgreementRepository agreementRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.agreementRepository = agreementRepository;
    }

    public String registerUser(AgreementRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return"Error: Nazwa użytkownika zajęta";
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return "Error: Email jest w zajęty";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();

        Company company = companyRepository.findById(userImpl.getId()).orElse(null);
        if(company == null){
            System.out.println("Firma nie istnieje");
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        // Create new Employee
        Employee employee = new Employee(
                signUpRequest.getName(),
                signUpRequest.getLastName(),
                signUpRequest.getPosition(),
                signUpRequest.getPhone(),
                signUpRequest.getStreet(),
                signUpRequest.getStreetNumber(),
                signUpRequest.getBuildingNumber(),
                signUpRequest.getCity(),
                signUpRequest.getPostcode(),
                user
        );

        // Create new agreement
        Agreement agreement = new Agreement(
                signUpRequest.getAgreementType(),
                signUpRequest.getAssignedDate(),
                signUpRequest.getDateFrom(),
                signUpRequest.getDateTo(),
                signUpRequest.getSalary(),
                signUpRequest.getMaxEmployees(),
                company,
                user,
                employee
        );


        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(employeeRole);
        } else {
            strRoles.forEach(role -> {
                if ("employee".equals(role)) {
                    Role employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(employeeRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        employeeRepository.save(employee);
        agreementRepository.save(agreement);

        return "Employee registered successfully!";
    }

}
