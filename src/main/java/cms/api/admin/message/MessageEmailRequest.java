package cms.api.admin.message;

import lombok.Data;

@Data
public class MessageEmailRequest {
    String answer;

    public String getAnswer() {
        return answer;
    }
}
