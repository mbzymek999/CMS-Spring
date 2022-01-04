package cms.domain.user.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private int id;
    @Column(unique = true, nullable = false)
    private String idClient;
    private String companyName;
    private String email;
    private String phone;
    private String message;

    public Message(String companyName, String email, String phone, String message) {
        this.companyName = companyName;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }

    public Message() {
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

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
