package cms.domain.employee.entity;

import cms.domain.company.entity.Task;
import cms.domain.user.entity.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Long id;

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

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "employeeTask")
    private Set<Task> employeeTask;

    public Employee(String name, String lastName, Long pesel, String position, String phone, String street, String streetNumber, String buildingNumber, String city, String postcode, User user) {
        this.name = name;
        this.lastName = lastName;
        this.pesel = pesel;
        this.position = position;
        this.phone = phone;
        this.street = street;
        this.streetNumber = streetNumber;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.postcode = postcode;
        this.user = user;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPesel() {
        return pesel;
    }

    public void setPesel(Long pesel) {
        this.pesel = pesel;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Task> getEmployeeTask() {
        return employeeTask;
    }

    public void setEmployeeTask(Set<Task> employeeTask) {
        this.employeeTask = employeeTask;
    }
}
