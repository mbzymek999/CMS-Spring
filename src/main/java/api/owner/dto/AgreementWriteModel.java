package api.owner.dto;;

import api.owner.entities.Agreement;
import api.owner.entities.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AgreementWriteModel {

    private String agreementType;
    private LocalDate assignedDate;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private double salary;

    public AgreementWriteModel(Agreement agreement) {
        this.agreementType = agreement.getAgreementType();
        this.assignedDate = agreement.getAssignedDate();
        this.dateFrom = agreement.getDateFrom();
        this.dateTo = agreement.getDateTo();
        this.salary = agreement.getSalary();
    }

    public Agreement toAgreement() {
        Agreement agreement = new Agreement();
        agreement.setAgreementType(this.agreementType);
        agreement.setAssignedDate(this.assignedDate);
        agreement.setDateFrom(this.dateFrom);
        agreement.setDateTo(this.dateTo);
        agreement.setSalary(this.salary);
        return agreement;
    }

}
