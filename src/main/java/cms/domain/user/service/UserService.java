package cms.domain.user.service;

import cms.api.user.UserRequest;
import cms.domain.user.entity.User;
import cms.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public String updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow();
        user.setPassword(encoder.encode(userRequest.getPassword()));
        userRepository.save(user);
        return "Hasło zostało zmienione";
    }

}
