package cms.api.company.agreement;

import cms.domain.company.entity.Agreement;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AgreementDetailCompanyReadModel {

    // agreement
    private String agreementType;
    private LocalDate assignedDate;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private double salary;

    //employee
    private String name;
    private String lastName;
    private Long pesel;
    private String position;
    private String streetEmployee;
    private String streetNumberEmployee;
    private String buildingNumberEmployee;
    private String cityEmployee;
    private String postcodeEmployee;

    //company
    private String companyName;
    private String nip;
    private String regon;
    private String representativePerson;
    private String streetCompany;
    private String streetNumberCompany;
    private String buildingNumberCompany;
    private String postcodeCompany;
    private String cityCompany;

    public AgreementDetailCompanyReadModel(Agreement agreement) {
        this.agreementType = agreement.getAgreementType();
        this.assignedDate = agreement.getAssignedDate();
        this.dateFrom = agreement.getDateFrom();
        this.dateTo = agreement.getDateTo();
        this.salary = agreement.getSalary();
        this.name = agreement.getEmployeeAgreement().getName();
        this.lastName = agreement.getEmployeeAgreement().getLastName();
        this.pesel = agreement.getEmployeeAgreement().getPesel();
        this.position = agreement.getEmployeeAgreement().getPosition();
        this.streetEmployee = agreement.getEmployeeAgreement().getStreet();
        this.streetNumberEmployee = agreement.getEmployeeAgreement().getStreetNumber();
        this.buildingNumberEmployee = agreement.getEmployeeAgreement().getBuildingNumber();
        this.cityEmployee = agreement.getEmployeeAgreement().getCity();
        this.postcodeEmployee = agreement.getEmployeeAgreement().getPostcode();
        this.companyName = agreement.getCompanyAgreement().getCompanyName();
        this.nip = agreement.getCompanyAgreement().getNip();
        this.regon = agreement.getCompanyAgreement().getRegon();
        this.representativePerson = agreement.getCompanyAgreement().getRepresentativePerson();
        this.streetCompany = agreement.getCompanyAgreement().getStreet();
        this.streetNumberCompany = agreement.getCompanyAgreement().getStreetNumber();
        this.buildingNumberCompany = agreement.getCompanyAgreement().getBuildingNumber();
        this.postcodeCompany = agreement.getCompanyAgreement().getPostcode();
        this.cityCompany = agreement.getCompanyAgreement().getCity();
    }
}
