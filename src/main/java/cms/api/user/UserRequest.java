package cms.api.user;

import lombok.Data;

@Data
public class UserRequest {
    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
