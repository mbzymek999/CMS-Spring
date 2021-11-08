package domain.owner.repositories;

import domain.owner.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

//    List<Task> findAllByEmployeeUserId(Long id);
//
//    List<Task> findAllByOwnerUserId(Long id);
//
//    Optional<Task> findById(int id);

}
