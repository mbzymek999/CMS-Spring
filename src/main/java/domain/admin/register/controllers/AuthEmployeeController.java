package domain.admin.register.controllers;

import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.user.models.ERole;
import domain.user.models.Role;
import domain.user.models.User;
import domain.admin.register.payload.request.SignupEmployeeRequest;
import domain.user.payload.response.MessageResponse;
import domain.user.repository.RoleRepository;
import domain.user.repository.UserRepository;
import domain.user.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthEmployeeController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signup/employee")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupEmployeeRequest signUpEmployeeRequest) {
        if (userRepository.existsByUsername(signUpEmployeeRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpEmployeeRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new employee user's account
        User user = new User(signUpEmployeeRequest.getUsername(),
                signUpEmployeeRequest.getName(),
                signUpEmployeeRequest.getLastName(),
                signUpEmployeeRequest.getPosition(),
                signUpEmployeeRequest.getPhone(),
                signUpEmployeeRequest.getStreet(),
                signUpEmployeeRequest.getStreetNumber(),
                signUpEmployeeRequest.getBuildingNumber(),
                signUpEmployeeRequest.getCity(),
                signUpEmployeeRequest.getPostcode(),
                signUpEmployeeRequest.getEmail(),
                encoder.encode(signUpEmployeeRequest.getPassword()));

        Set<String> strRoles = signUpEmployeeRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "employee":
                        Role ownerRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(ownerRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Employee registered successfully!"));
    }
}
