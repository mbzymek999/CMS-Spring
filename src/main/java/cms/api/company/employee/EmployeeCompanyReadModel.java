package cms.api.company.employee;

import cms.domain.company.entity.Agreement;
import cms.domain.employee.entity.Employee;
import lombok.Data;

@Data
public class EmployeeCompanyReadModel {
    private Long employeeId;
    private String name;
    private String lastName;
    private String position;
    private String phone;
    private String email;
    private String street;
    private String streetNumber;
    private String buildingNumber;
    private String city;
    private String postcode;

    public EmployeeCompanyReadModel(Agreement agreement) {
        this.employeeId = agreement.getEmployeeAgreement().getId();
        this.name = agreement.getEmployeeAgreement().getName();
        this.lastName = agreement.getEmployeeAgreement().getLastName();
        this.position = agreement.getEmployeeAgreement().getPosition();
        this.phone = agreement.getEmployeeAgreement().getPhone();
        this.email = agreement.getEmployeeAgreement().getUser().getEmail();
    }

    public EmployeeCompanyReadModel(Employee employee) {
        this.employeeId = employee.getId();
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
