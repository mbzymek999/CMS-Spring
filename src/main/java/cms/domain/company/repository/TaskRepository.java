package cms.domain.company.repository;

import cms.domain.company.entity.Payment;
import cms.domain.company.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Integer> {

        @Override
        <S extends Task> S save(S s);

//        List<Task> findAllByCompanyTask(Long id);

        List<Task> findAllByCompanyTask_User_Id(Long id);

        List<Task> findAllByEmployeeTask_User_Id(Long id);

}
