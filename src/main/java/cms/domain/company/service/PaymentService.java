package cms.domain.company.service;

import cms.api.company.request.PaymentRequest;
import cms.config.security.services.UserDetailsImpl;
import cms.domain.admin.entity.Invoice;
import cms.domain.admin.repository.InvoiceRepository;
import cms.domain.company.entity.Company;
import cms.domain.company.entity.Payment;
import cms.domain.company.repository.CompanyRepository;
import cms.domain.company.repository.PaymentRepository;
import cms.domain.employee.entity.Employee;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;
    private final CompanyRepository companyRepository;

    public PaymentService(InvoiceRepository invoiceRepository, PaymentRepository paymentRepository, CompanyRepository companyRepository) {
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
        this.companyRepository = companyRepository;
    }

    public String createPayment(PaymentRequest paymentRequest, Long companyId) {

        Company company = companyRepository.findById(companyId).orElse(null);
        if(company == null){
            System.out.println("Firma nie istnieje");
        }

        // Create new invoice
        Invoice invoice = new Invoice(
                paymentRequest.getDateFrom(),
                paymentRequest.getDateTo(),
                paymentRequest.getPricePackage()
        );

        // Create new payment
        Payment payment = new Payment(
                paymentRequest.getDateInvoice(),
                paymentRequest.getPrice(),
                paymentRequest.isPaymentDone(),
                invoice
        );

        invoice.setCompany(company);
        invoiceRepository.save(invoice);
        paymentRepository.save(payment);

        return "Payment and Invoice created.!";
    }
}
