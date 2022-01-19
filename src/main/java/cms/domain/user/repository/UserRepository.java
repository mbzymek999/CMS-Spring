package cms.domain.user.repository;

import cms.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByIdClient(String idClient);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
