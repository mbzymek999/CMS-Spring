package cms.api.employee.dto;

import cms.domain.employee.entity.Employee;
import lombok.Data;

@Data
public class EmployeeReadModel {
    private Long id;
    private String name;
    private String lastName;
    private String position;
    private String phone;
    private String street;
    private String streetNumber;
    private String buildingNumber;
    private String city;
    private String postcode;

    public EmployeeReadModel(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.lastName = employee.getLastName();
        this.position = employee.getPosition();
        this.phone = employee.getPhone();
        this.street = employee.getStreet();
        this.streetNumber = employee.getStreetNumber();
        this.buildingNumber = employee.getBuildingNumber();
        this.city = employee.getCity();
        this.postcode = employee.getPostcode();
    }
}
