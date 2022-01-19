package cms.domain.company.repository;

import cms.domain.company.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

        @Override
        <S extends Task> S save(S s);

        Page<Task> findAllByEmployeeTask_User_IdClientAndStatusTask(Pageable page, String idClient, int statusTask);

        Page<Task> findAllByCompanyTask_User_IdClient(Pageable page, String id);

        List<Task> findAllByCompanyTask_User_IdClient(String id);

        Page<Task> findAllByCompanyTask_User_IdClientAndStatusTask(Pageable page, String idClient, int statusTask);

}
