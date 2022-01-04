package cms.api.user;

import cms.domain.user.service.MessageUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class MessageUserController {

    private final MessageUserService service;
    Logger logger = LoggerFactory.getLogger(MessageUserController.class);

    public MessageUserController(MessageUserService service) {
        this.service = service;
    }


    @PostMapping("/message/send")
    public ResponseEntity<String> sendNewMessage(@RequestBody @Valid MessageRequest request) {
        return ResponseEntity.ok(service.sendMessage(request));
    }

}
