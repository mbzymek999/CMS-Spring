package cms.api.admin.message;

import cms.domain.user.entity.Message;
import lombok.Data;

@Data
public class MessageReadModel {
    private String companyName;
    private String email;
    private String phone;
    private String message;

    public MessageReadModel(Message message) {
        this.companyName = message.getCompanyName();
        this.email = message.getEmail();
        this.phone = message.getPhone();
        this.message = message.getMessage();
    }
}
