package cms.domain.user.serviceImpl;

import cms.api.user.MessageRequest;

public interface MessageUserServiceImpl {

    String sendMessage(MessageRequest messageRequest);
}
