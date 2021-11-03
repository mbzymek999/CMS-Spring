package api.owner.entities;

import api.config.security.jwt.models.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "agreements")
public class Agreement {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private int id;
    private String agreementType;
    private LocalDate assignedDate;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private double salary;

    //wlasciciel moze miec wiele umow
    @JoinColumn(name = "owner_id")
    @ManyToOne
    private User ownerAgreement;

    //pracownik moze miec 1 umowe
    @JoinColumn(name = "employee_id")
    @OneToOne
    private User employeeAgreement;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public User getOwnerAgreement() {
        return ownerAgreement;
    }

    public void setOwnerAgreement(User ownerAgreement) {
        this.ownerAgreement = ownerAgreement;
    }

    public User getEmployeeAgreement() {
        return employeeAgreement;
    }

    public void setEmployeeAgreement(User employeeAgreement) {
        this.employeeAgreement = employeeAgreement;
    }
}
