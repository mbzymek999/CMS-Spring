package cms.domain.user.service;

import cms.api.user.MessageRequest;
import cms.domain.user.entity.Message;
import cms.domain.user.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class MessageUserService {

    private final MessageRepository messageRepository;

    public MessageUserService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public String sendMessage(MessageRequest messageRequest) {

        String randomId = UUID.randomUUID().toString().replace("-", "");

        if (messageRepository.existsByIdClient(randomId)) {
            throw new IllegalArgumentException("Error: Wysłanie wiadodmości nie powiodło się");
        }

        Message message = new Message(
                messageRequest.getCompanyName(),
                messageRequest.getEmail(),
                messageRequest.getPhone(),
                messageRequest.getMessage()
        );

        message.setIdClient(randomId);
        messageRepository.save(message);

        return "Ok";
    }
}
