package cms.domain.company.repository;

import cms.domain.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(Company Company);

    List<Company> findAllByNipOrCompanyName(String nip, String companyName);

    List<Company> findAllByNip(String nip);

    List<Company> findAllByCompanyName(String companyName);

}
