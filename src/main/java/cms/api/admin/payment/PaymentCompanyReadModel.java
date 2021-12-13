package cms.api.admin.payment;

import cms.domain.company.entity.Company;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentCompanyReadModel {

    private Long id;
    private String companyName;
    private double pricePackage;


    public PaymentCompanyReadModel(Company company) {
        this.id = company.getId();
        this.companyName = company.getCompanyName();
        this.pricePackage = company.getMaxEmployees() * 10;
    }
}