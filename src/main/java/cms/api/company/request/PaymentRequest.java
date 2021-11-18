package cms.api.company.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PaymentRequest {

    // invoice
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;
    private double pricePackage;

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
}
