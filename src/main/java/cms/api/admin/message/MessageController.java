package cms.api.admin.message;

import cms.domain.admin.serviceImpl.MessageAdminServiceImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MessageController {

    private final MessageAdminServiceImpl messageAdminServiceImpl;

    public MessageController(MessageAdminServiceImpl messageAdminServiceImpl) {
        this.messageAdminServiceImpl = messageAdminServiceImpl;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/message/read")
    List<MessageReadModel> readAllMessage() {
        return messageAdminServiceImpl.readAll();
    }

    @GetMapping
    @RequestMapping("/message/read/{idClient}")
    @PreAuthorize("hasRole('ADMIN')")
    MessageDetailReadModel readMessageDetails(@PathVariable String idClient) {
        return messageAdminServiceImpl.readDetailMessage(idClient);
    }

    @PostMapping("/message/email")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public String sendEmail(@RequestBody @Valid MessageEmailRequest request, @Param("clientEmail") String clientEmail) {
        return messageAdminServiceImpl.sendEmail(request, clientEmail);
    }

}
