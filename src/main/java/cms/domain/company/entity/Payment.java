package cms.domain.company.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private int id;
    private LocalDate datePayment;
    private double price;
    private boolean paymentDone;
    private LocalDate termPayment;

    @JoinColumn(name = "company_id")
    @ManyToOne
    private Company company;

    public Payment() {
    }

    public Payment(LocalDate datePayment, double price) {
        this.datePayment = datePayment;
        this.price = price;
    }

    public Payment(LocalDate datePayment, double price, boolean paymentDone) {
        this.datePayment = datePayment;
        this.price = price;
        this.paymentDone = paymentDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(LocalDate datePayment) {
        this.datePayment = datePayment;
    }

    public LocalDate getTermPayment() {
        return termPayment;
    }

    public void setTermPayment(LocalDate termPayment) {
        this.termPayment = termPayment;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPaymentDone() {
        return paymentDone;
    }

    public void setPaymentDone(boolean paymentDone) {
        this.paymentDone = paymentDone;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
