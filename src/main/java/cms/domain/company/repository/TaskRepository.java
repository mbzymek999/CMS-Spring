package cms.domain.company.repository;

import cms.domain.company.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Integer> {

        Optional<Task> findById(int id);
}
