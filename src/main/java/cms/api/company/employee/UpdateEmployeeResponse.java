package cms.api.company.employee;

import cms.domain.employee.entity.Employee;
import lombok.Data;

@Data
public class UpdateEmployeeResponse {

    private String position;
    private String phone;
    private String street;
    private String streetNumber;
    private String buildingNumber;
    private String city;
    private String postcode;

    public void updateEntity(Employee employee) {
        employee.setPosition(this.position);
        employee.setPhone(this.phone);
        employee.setStreet(this.street);
        employee.setStreetNumber(this.streetNumber);
        employee.setBuildingNumber(this.buildingNumber);
        employee.setCity(this.city);
        employee.setPostcode(this.postcode);
    }

}
