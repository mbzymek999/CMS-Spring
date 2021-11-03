package api.owner.service;

import api.config.security.jwt.models.User;
import api.config.security.jwt.repository.UserRepository;
import api.owner.entities.Agreement;
import api.owner.repositories.AgreementRepository;
import api.owner.request.AgreementRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import api.config.security.jwt.security.services.UserDetailsImpl;


@Service
public class AgreementService {

    private final AgreementRepository repository;
    private final UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(AgreementService.class);

    public AgreementService(AgreementRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public String addNewAgreement(AgreementRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();

        User user = userRepository.findById(userImpl.getId()).orElse(null);
        if(user == null){
            System.out.println("User is null");
        }

        Agreement agreement = request.getAgreement().toAgreement();

        agreement.setOwnerAgreement(user);

        repository.save(agreement);

        return "Ok";
    }
}
