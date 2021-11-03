package api.owner.repositories;

import api.owner.entities.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Integer> {

    Optional<Agreement> findById(int id);
}
