package cms.domain.company.service;

import cms.api.company.agreement.AgreementCompanyReadModel;
import cms.api.company.agreement.AgreementDetailCompanyReadModel;
import cms.api.company.agreement.AgreementRequest;
import cms.domain.company.entity.Agreement;
import cms.domain.company.entity.Company;
import cms.domain.company.repository.AgreementRepository;
import cms.domain.company.serviceImpl.AgreementServiceImpl;
import cms.domain.employee.entity.Employee;
import cms.domain.employee.repository.EmployeeRepository;
import cms.domain.user.entity.ERole;
import cms.domain.user.entity.Role;
import cms.domain.user.entity.User;
import cms.domain.user.repository.RoleRepository;
import cms.domain.user.repository.UserRepository;
import cms.external.email.facade.EmailServiceFacade;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AgreementService implements AgreementServiceImpl {

    private final UserRepository userRepository;

    private final EmployeeRepository employeeRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final AgreementRepository agreementRepository;

    private final EmailServiceFacade mailServiceFacade;

    public AgreementService(UserRepository userRepository, EmployeeRepository employeeRepository, RoleRepository roleRepository, PasswordEncoder encoder, AgreementRepository agreementRepository, EmailServiceFacade mailServiceFacade) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.agreementRepository = agreementRepository;
        this.mailServiceFacade = mailServiceFacade;
    }

    private final String randomPassword = UUID.randomUUID().toString().replace("-", "");

    public String registerUser(AgreementRequest signUpRequest, String idClient) {

        checkIfUserNameAlreadyExist(signUpRequest);

        checkIfEmailAlreadyExist(signUpRequest);

//        User userId = userRepository.findById(id).orElse(null);
//        Company company = userId.getCompanyUser();

        User userId = userRepository.findByIdClient(idClient).orElse(null);
        Company company = userId.getCompanyUser();

        checkIfCompanyExist(company);

        System.out.println();

        String randomId = UUID.randomUUID().toString().replace("-", "");

        // Create new user's account
        User user = new User(
                randomId,
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(randomPassword));


        // Create new Employee
        Employee employee = new Employee(
                signUpRequest.getName(),
                signUpRequest.getLastName(),
                signUpRequest.getPesel(),
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
                signUpRequest.getBankAccount(),
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

        sendEmail(signUpRequest);

        user.setRoles(roles);
        userRepository.save(user);
        employeeRepository.save(employee);
        agreementRepository.save(agreement);

        return "Employee registered successfully!";
    }

    public List<AgreementCompanyReadModel> readCompanyAgreements(String idClient) {
        return agreementRepository.findAllByCompanyAgreement_User_IdClient(idClient).stream().map(AgreementCompanyReadModel::new).collect(Collectors.toList());
    }

    public AgreementDetailCompanyReadModel readCompanyDetails(String idClient, int agreementId) {
        Agreement agreement = agreementRepository.findAllByCompanyAgreement_User_IdClientAndId(idClient, agreementId).orElse(null);
        if (agreement != null) {
            return new AgreementDetailCompanyReadModel(agreement);
        }
        throw new NullPointerException("AgreementDetailCompanyReadModel - returned value from repository is null");
    }

    public void sendEmail(AgreementRequest signUpRequest) {
        String emailBody = "Login: " + signUpRequest.getUsername() + "\n" + "Hasło: " + randomPassword;
        mailServiceFacade.sendEmail(signUpRequest.getEmail(), "Dane logowania pracownika", emailBody);
    }

    public void checkIfUserNameAlreadyExist(AgreementRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new IllegalArgumentException("Error: Nazwa użytkownika zajęta");
        }
    }

    public void checkIfEmailAlreadyExist(AgreementRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new IllegalArgumentException("Error: Email jest już zajęty");
        }
    }

    public void checkIfCompanyExist(Company company) {
        if(company == null){
            throw new IllegalArgumentException("Firma nie istnieje");
        }
    }

}
