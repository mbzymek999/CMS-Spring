package cms.domain.company.entity;

import cms.domain.employee.entity.Employee;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private int id;
    private String name;
    private String type;
    private String description;
    private LocalDate createdDate;
    private boolean isAccepted;
    private LocalDate dateTo;

    //firma moze miec wiele zadan
    @JoinColumn(name = "company_id")
    @ManyToOne
    private Company companyTask;

    //pracownik moze miec wiele zadan
    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employeeTask;

    public Task(String name, String type, String description, LocalDate dateTo) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.dateTo = dateTo;
    }

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Company getCompanyTask() {
        return companyTask;
    }

    public void setCompanyTask(Company companyTask) {
        this.companyTask = companyTask;
    }

    public Employee getEmployeeTask() {
        return employeeTask;
    }

    public void setEmployeeTask(Employee employeeTask) {
        this.employeeTask = employeeTask;
    }
}