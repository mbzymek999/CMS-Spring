package cms.api.admin.message;

import cms.domain.user.entity.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDetailReadModel {
    String idClient;
    String emailMessage;
    String message;
    String companyName;

    public MessageDetailReadModel(Message message) {
        this.idClient = message.getIdClient();
        this.emailMessage = message.getEmail();
        this.message = message.getMessage();
        this.companyName = message.getCompanyName();
    }

}
