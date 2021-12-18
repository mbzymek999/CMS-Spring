package cms.api.admin.payment;

import cms.domain.company.entity.Payment;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data

public class PaymentAdminResponse {

    Long totalElements;
    int totalPages;
    int currentPage;
    List<PaymentReadModel> payments;

    public PaymentAdminResponse(Page<Payment> page, List<PaymentReadModel> payments) {
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber();
        this.payments = payments;
    }
}
