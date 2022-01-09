package cms.domain.admin.service;

import cms.api.admin.message.MessageDetailReadModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class MessageAdminServiceTest {

    @Autowired
    private MessageAdminService service;

    @Test
    void readDetailMessage() {
        // given
        // when
        MessageDetailReadModel messageDetailReadModel = service.readDetailMessage("a3c7266ce9fd4c77954a53b9526cd444");
        // then
        assertThat(messageDetailReadModel).isNotNull();
        assertThat(messageDetailReadModel.getIdClient()).isEqualTo("a3c7266ce9fd4c77954a53b9526cd444");

    }
}