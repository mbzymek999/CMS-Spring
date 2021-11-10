package cms.domain.company.repository;

import cms.domain.company.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Integer> {
    Optional<Agreement> findById(int id);

}
