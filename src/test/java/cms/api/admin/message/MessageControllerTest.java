package cms.api.admin.message;

import cms.domain.user.entity.Message;
import cms.domain.user.repository.MessageRepository;
import cms.domain.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void readAllMessage() {
        // given
        MessageController messageController = mock(MessageController.class);
        when(messageController.readAllMessage()).thenReturn(prepareMockData());
        // when
        List<MessageReadModel> messageReadModels = messageController.readAllMessage();
        // then
        Assert.assertThat(messageReadModels, Matchers.hasSize(2));
    }

    private List<MessageReadModel> prepareMockData() {
        Message message1 = new Message("abcdef1234", "Firma Test 1",
                "test1@example.pl", "123456788", "Wiadomosc testowa 1");
        Message message2 = new Message("zaqwer1234", "Firma Test2",
                "test2@example.pl", "4444446788", "Wiadomosc testowa 2");

        List<MessageReadModel> messages = new ArrayList<>();
        messages.add(new MessageReadModel(message1));
        messages.add(new MessageReadModel(message2));

        return messages;
    }

    @Test
    @Transactional
    void readMessageDetails() throws Exception {
        // given
        Message newMessage = new Message();
        String randomId = UUID.randomUUID().toString().replace("-", "");
        newMessage.setIdClient(randomId);
        newMessage.setCompanyName("Firma test 3");
        newMessage.setEmail("test3@example.pl");
        newMessage.setMessage("Wiadomosc testowa 3");
        messageRepository.save(newMessage);
        // when
        MvcResult mvcResult = mockMvc.perform(get("/message/read/" + newMessage.getIdClient()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        // then
        MessageDetailReadModel messageDetailReadModel = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), MessageDetailReadModel.class);
        assertThat(messageDetailReadModel).isNotNull();
        assertThat(messageDetailReadModel.getIdClient()).isEqualTo(newMessage.getIdClient());
        assertThat(messageDetailReadModel.getCompanyName()).isEqualTo(newMessage.getCompanyName());
        assertThat(messageDetailReadModel.getEmailMessage()).isEqualTo(newMessage.getEmail());
        assertThat(messageDetailReadModel.getMessage()).isEqualTo(newMessage.getMessage());
    }
}