package cms.api.company.employee;

import cms.domain.company.entity.Agreement;
import lombok.Data;

@Data
public class EmployeeCompanyReadModel {
    private Long employeeId;
    private String name;
    private String lastName;
    private String position;
    private String phone;
    private String email;

    public EmployeeCompanyReadModel(Agreement agreement) {
        this.employeeId = agreement.getEmployeeAgreement().getId();
        this.name = agreement.getEmployeeAgreement().getName();
        this.lastName = agreement.getEmployeeAgreement().getLastName();
        this.position = agreement.getEmployeeAgreement().getPosition();
        this.phone = agreement.getEmployeeAgreement().getPhone();
        this.email = agreement.getEmployeeAgreement().getUser().getEmail();
    }
}
