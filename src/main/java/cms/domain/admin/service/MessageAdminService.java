package cms.domain.admin.service;

import cms.api.admin.message.MessageDetailReadModel;
import cms.api.admin.message.MessageReadModel;
import cms.domain.user.entity.Message;
import cms.domain.user.repository.MessageRepository;
import cms.external.email.service.MailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageAdminService {

    private final MailService mailService;
    private final MessageRepository repository;

    public MessageAdminService(MailService mailService, MessageRepository repository) {
        this.mailService = mailService;
        this.repository = repository;
    }

    public List<MessageReadModel> readAll() {
        return repository.findAll().stream().map(MessageReadModel::new).collect(Collectors.toList());
    }

    public MessageDetailReadModel readDetailMessage(String idClient) {
        Message message = repository.findByIdClient(idClient).orElse(null);
        if (message != null) {
            return new MessageDetailReadModel(message);
        }
        throw new NullPointerException("MessageDetailReadModel - returned value from repository is null");
    }
}
