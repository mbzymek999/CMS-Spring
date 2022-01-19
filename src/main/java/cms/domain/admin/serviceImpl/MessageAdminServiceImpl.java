package cms.domain.admin.serviceImpl;

import cms.api.admin.message.MessageDetailReadModel;
import cms.api.admin.message.MessageEmailRequest;
import cms.api.admin.message.MessageReadModel;

import java.util.List;

public interface MessageAdminServiceImpl {

    List<MessageReadModel> readAll();

    MessageDetailReadModel readDetailMessage(String idClient);

    String sendEmail(MessageEmailRequest request, String clientEmail);
}
