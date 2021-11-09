package cms.domain.user.entity;

import cms.domain.employee.entity.Employee;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @Size(max = 50)
    private String companyName;

    @Size(max = 20)
    private String shortCompanyName;

    @Size(max = 10)
    private String nip;

    private String regon;

    private String phone;

    private String street;

    private String streetNumber;

    private String buildingNumber;

    private String city;

    private String postcode;

    private String province;

    private String country;

    private String additionalFields;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @OneToOne(mappedBy = "user")
    private Employee employeeUser;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(@NotBlank @Size(max = 20) String username, @Size(max = 50) String companyName, @Size(max = 20) String shortCompanyName, @Size(max = 10) String nip, String regon, String phone, String street, String streetNumber, String buildingNumber, String city, String postcode, String province, String country, String additionalFields, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password) {
        this.username = username;
        this.companyName = companyName;
        this.shortCompanyName = shortCompanyName;
        this.nip = nip;
        this.regon = regon;
        this.phone = phone;
        this.street = street;
        this.streetNumber = streetNumber;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.postcode = postcode;
        this.province = province;
        this.country = country;
        this.additionalFields = additionalFields;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setShortCompanyName(String shortCompanyName) {
        this.shortCompanyName = shortCompanyName;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
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

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAdditionalFields() {
        return additionalFields;
    }

    public void setAdditionalFields(String additionalFields) {
        this.additionalFields = additionalFields;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Employee getEmployeeUser() {
        return employeeUser;
    }

    public void setEmployeeUser(Employee employeeUser) {
        this.employeeUser = employeeUser;
    }
}
