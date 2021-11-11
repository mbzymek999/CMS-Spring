package cms.domain.company.repository;

import cms.domain.company.entity.AgreementTest;
import cms.domain.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface AgreementTestRepository extends JpaRepository<AgreementTest, Long> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(AgreementTest agreementTest);
}