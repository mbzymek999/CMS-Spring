package cms.api.admin.message;

import cms.domain.admin.service.MessageAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MessageController {

    private final MessageAdminService service;
    Logger logger = LoggerFactory.getLogger(MessageController.class);

    public MessageController(MessageAdminService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/message/read")
    List<MessageReadModel> readAllMessage() {
        return service.readAll();
    }

    @GetMapping
    @RequestMapping("/message/read/{idClient}")
    @PreAuthorize("hasRole('ADMIN')")
    MessageDetailReadModel readCompanyAgreementDetails(@PathVariable String idClient) {
        return service.readDetailMessage(idClient);
    }

}
