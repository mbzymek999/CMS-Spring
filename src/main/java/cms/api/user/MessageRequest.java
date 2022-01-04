package cms.api.user;

import lombok.Data;

@Data
public class MessageRequest {
    private String companyName;
    private String email;
    private String phone;
    private String message;

    public String getCompanyName() {
        return companyName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getMessage() {
        return message;
    }
}
