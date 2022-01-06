package cms.api.admin.message;

import cms.domain.user.entity.Message;
import lombok.Data;

@Data
public class MessageDetailReadModel {
    String idClient;
    String emailMessage;

    public MessageDetailReadModel(Message message) {
        this.idClient = message.getIdClient();
        this.emailMessage = message.getEmail();
    }
}
