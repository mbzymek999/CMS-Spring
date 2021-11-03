package api.owner.repositories;

import api.owner.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

//    List<Task> findAllByEmployeeUserId(Long id);
//
//    List<Task> findAllByOwnerUserId(Long id);
//
//    Optional<Task> findById(int id);

}
