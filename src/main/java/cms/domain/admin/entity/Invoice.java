package cms.domain.admin.entity;

import cms.config.payment.entity.Payment;
import cms.domain.company.entity.Company;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private int id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private double pricePackage;
    @JoinColumn(name = "company_id")
    @ManyToOne
    private Company company;
    @OneToOne(mappedBy = "invoice", cascade=CascadeType.ALL)
    private Payment payment;

    public Invoice() {
    }

    public Invoice(LocalDate dateFrom, LocalDate dateTo, double pricePackage, Company company, Payment payment) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.pricePackage = pricePackage;
        this.company = company;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPricePackage() {
        return pricePackage;
    }

    public void setPricePackage(double pricePackage) {
        this.pricePackage = pricePackage;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
