package cms.api.company.agreement;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
public class AgreementRequest {

    //user
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
//    @NotBlank
//    @Size(min = 6, max = 40)
//    private String password;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private Set<String> role;

    // agreement
    private String agreementType;
    private LocalDate assignedDate;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private double salary;
    private String bankAccount;

    //employee
    private String name;
    private String lastName;
    private Long pesel;
    private String position;
    private String phone;
    private String street;
    private String streetNumber;
    private String buildingNumber;
    private String city;
    private String postcode;

    public AgreementRequest validate() throws Exception {
        if(username.equals(email)) {
            throw new Exception("nazwa użytkownika nie może być taka sama jak e-mail");
        }
        if (dateFrom.isAfter(dateTo)) {
            throw new Exception("Data rozpoczęcia musi być wcześniej od daty zakończenia");
        } else if(dateFrom.isEqual(dateTo)){
            throw new Exception("Data rozpoczęcia nie może być taka sama jak data zakończenia");
        }
        if (assignedDate.isAfter(dateFrom)) {
            throw new Exception("Data podpisania nie może być po dacie rozpoczęcia");
        }
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Set<String> getRole() {
        return role;
    }

    public String getAgreementType() {
        return agreementType;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public double getSalary() {
        return salary;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getPesel() {
        return pesel;
    }

    public String getPosition() {
        return position;
    }

    public String getPhone() {
        return phone;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }
}
