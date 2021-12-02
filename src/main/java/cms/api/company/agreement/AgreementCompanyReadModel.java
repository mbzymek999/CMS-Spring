package cms.api.company.agreement;

import cms.domain.company.entity.Agreement;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AgreementCompanyReadModel {
    private String agreementType;
    private LocalDate assignedDate;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Long employeeId;
    private double salary;
    private String name;
    private String lastName;

    public AgreementCompanyReadModel(Agreement agreement) {
        this.agreementType = agreement.getAgreementType();
        this.assignedDate = agreement.getAssignedDate();
        this.dateFrom = agreement.getDateFrom();
        this.dateTo = agreement.getDateTo();
        this.salary = agreement.getSalary();
        this.employeeId = agreement.getEmployeeAgreement().getId();
        this.name = agreement.getEmployeeAgreement().getName();
        this.lastName = agreement.getEmployeeAgreement().getLastName();
    }
}
