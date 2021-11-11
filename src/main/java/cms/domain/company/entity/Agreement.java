package cms.domain.company.entity;

import cms.domain.employee.entity.Employee;
import cms.domain.user.entity.User;
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
    @JoinColumn(name = "company_id")
    @ManyToOne
    private Company companyAgreement;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employeeAgreement;

    public Agreement(String agreementType, LocalDate assignedDate, LocalDate dateFrom, LocalDate dateTo, double salary, User user, Employee employeeAgreement) {
        this.agreementType = agreementType;
        this.assignedDate = assignedDate;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.salary = salary;
        this.user = user;
        this.employeeAgreement = employeeAgreement;
    }

    public Agreement() {
    }

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

    public Company getCompanyAgreement() {
        return companyAgreement;
    }

    public void setCompanyAgreement(Company companyAgreement) {
        this.companyAgreement = companyAgreement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employee getEmployeeAgreement() {
        return employeeAgreement;
    }

    public void setEmployeeAgreement(Employee employeeAgreement) {
        this.employeeAgreement = employeeAgreement;
    }
}
