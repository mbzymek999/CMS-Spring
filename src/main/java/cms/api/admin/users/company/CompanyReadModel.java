package cms.api.admin.users.company;

import cms.domain.company.entity.Company;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyReadModel {

    private String companyName;
    private String nip;
    private String phone;
    private String city;
    private String email;

    public CompanyReadModel(Company company) {
        this.companyName = company.getCompanyName();
        this.nip = company.getNip();
        this.phone = company.getPhone();
        this.email = company.getUser().getEmail();
        this.city = company.getCity();
    }
}
