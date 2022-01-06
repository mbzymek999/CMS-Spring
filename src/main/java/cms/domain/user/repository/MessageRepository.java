package cms.domain.user.repository;

import cms.domain.user.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    <S extends Message> S save(S s);

    Boolean existsByIdClient(String idClient);

    Optional<Message> findByIdClient(String idClient);

}
