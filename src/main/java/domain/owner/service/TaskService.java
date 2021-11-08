package domain.owner.service;

import domain.user.models.User;
import domain.user.repository.UserRepository;
import domain.owner.entities.Task;
import domain.owner.repositories.TaskRepository;
import domain.owner.request.TaskRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import domain.user.security.services.UserDetailsImpl;


@Service
public class TaskService {

    private final TaskRepository repository;
    private final UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public String addNewTask(TaskRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userImpl = (UserDetailsImpl)authentication.getPrincipal();

        User user = userRepository.findById(userImpl.getId()).orElse(null);
        if(user == null){
            System.out.println("User is null");
        }

        Task task = request.getTask().toTask();

        task.setOwner(user);

        repository.save(task);

        return "Ok";
    }
}
