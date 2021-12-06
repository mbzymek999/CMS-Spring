package cms.domain.company.repository;

import cms.domain.company.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Integer> {

        @Override
        <S extends Task> S save(S s);

//        List<Task> findAllByCompanyTask(Long id);

        List<Task> findAllByEmployeeTask_User_IdAndStatusTask(Long id, int statusTask);

        List<Task> findAllByCompanyTask_User_Id(Pageable page, Long id);
}
