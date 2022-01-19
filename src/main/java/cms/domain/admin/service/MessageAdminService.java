package cms.domain.admin.service;

import cms.api.admin.message.MessageDetailReadModel;
import cms.api.admin.message.MessageEmailRequest;
import cms.api.admin.message.MessageReadModel;
import cms.domain.admin.serviceImpl.MessageAdminServiceImpl;
import cms.domain.user.entity.Message;
import cms.domain.user.repository.MessageRepository;
import cms.external.email.service.MailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageAdminService implements MessageAdminServiceImpl {

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

    public String sendEmail(MessageEmailRequest request, String clientEmail) {
        String emailBody = request.getAnswer();
        mailService.sendEmail(clientEmail, "CmsSoftware", emailBody);
        return "Email został wysłany!";
    }

}
