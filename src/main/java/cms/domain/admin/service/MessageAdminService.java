package cms.domain.admin.service;

import cms.api.admin.message.MessageReadModel;
import cms.domain.user.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageAdminService {

    private final MessageRepository repository;

    public MessageAdminService(MessageRepository repository) {
        this.repository = repository;
    }

    public List<MessageReadModel> readAll() {
        return repository.findAll().stream().map(MessageReadModel::new).collect(Collectors.toList());
    }
}
