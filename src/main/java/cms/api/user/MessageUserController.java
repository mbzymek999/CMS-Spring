package cms.api.user;

import cms.domain.user.serviceImpl.MessageUserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class MessageUserController {

    private final MessageUserServiceImpl messageUserServiceImpl;

    public MessageUserController(MessageUserServiceImpl messageUserServiceImpl) {
        this.messageUserServiceImpl = messageUserServiceImpl;
    }

    @PostMapping("/message/send")
    public ResponseEntity<String> sendNewMessage(@RequestBody @Valid MessageRequest request) {
        return ResponseEntity.ok(messageUserServiceImpl.sendMessage(request));
    }

}
