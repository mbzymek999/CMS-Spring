package cms.domain.company.repository;

import cms.domain.company.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    @Override
    <S extends Payment> S save(S s);

//        List<Payment> findAllCompanies(Long id);
}
