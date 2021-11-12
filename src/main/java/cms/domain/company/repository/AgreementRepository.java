package cms.domain.company.repository;

import cms.domain.company.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Integer> {
    Optional<Agreement> findById(int id);

    @Override
    @RestResource(exported = false)
    void deleteById(Integer id);

    @Override
    @RestResource(exported = false)
    void delete(Agreement agreementTest);

}