package cms.domain.company.entity;

import cms.domain.admin.entity.Invoice;
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
    @JoinColumn(name = "invoice_id")
    @OneToOne
    private Invoice invoice;

    public Payment() {
    }

    public Payment(LocalDate datePayment, double price, Invoice invoice) {
        this.datePayment = datePayment;
        this.price = price;
        this.invoice = invoice;
    }

    public Payment(LocalDate datePayment, double price, boolean paymentDone, Invoice invoice) {
        this.datePayment = datePayment;
        this.price = price;
        this.paymentDone = paymentDone;
        this.invoice = invoice;
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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

}
