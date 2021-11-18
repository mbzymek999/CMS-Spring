package cms.config.payment.request;

import lombok.Data;

@Data
public class ChargeRequest {

    public enum Currency {
        EUR, USD, PLN;
    }
    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
    private int paymentId;


    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getStripeEmail() {
        return stripeEmail;
    }

    public String getStripeToken() {
        return stripeToken;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setStripeEmail(String stripeEmail) {
        this.stripeEmail = stripeEmail;
    }

    public void setStripeToken(String stripeToken) {
        this.stripeToken = stripeToken;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
}