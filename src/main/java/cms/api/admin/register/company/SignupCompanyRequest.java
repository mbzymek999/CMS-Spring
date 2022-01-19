package cms.api.admin.register.company;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupCompanyRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    private String companyName;

    private String shortCompanyName;

    @NotBlank
    private String nip;

    @NotBlank
    private String regon;

    private String representativePerson;

    @NotBlank
    private String phone;

    private String street;

    @NotBlank
    private String streetNumber;

    private String buildingNumber;

    @NotBlank
    private String city;

    @NotBlank
    private String postcode;

    private String province;

    private String country;

    private String additionalFields;

    private int maxEmployees;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getShortCompanyName() {
        return shortCompanyName;
    }

    public String getNip() {
        return nip;
    }

    public String getRegon() {
        return regon;
    }

    public String getRepresentativePerson() {
        return representativePerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public String getAdditionalFields() {
        return additionalFields;
    }

    public int getMaxEmployees() {
        return maxEmployees;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

}
