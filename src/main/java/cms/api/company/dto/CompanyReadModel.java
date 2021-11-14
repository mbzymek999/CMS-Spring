package cms.api.company.dto;

import cms.domain.company.entity.Company;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CompanyReadModel {

    private Long id;

    private String companyName;

    private String shortCompanyName;

    private String nip;

    private String regon;

    private String phone;

    private String street;

    private String streetNumber;

    private String buildingNumber;

    private String city;

    private String postcode;

    private String province;

    private String country;

    private String additionalFields;

    public CompanyReadModel(Company company) {
        this.id = company.getId();
        this.companyName = company.getCompanyName();
        this.shortCompanyName = company.getShortCompanyName();
        this.nip = company.getNip();
        this.regon = company.getRegon();
        this.phone = company.getPhone();
        this.street = company.getStreet();
        this.streetNumber = company.getStreet();
        this.buildingNumber = company.getBuildingNumber();
        this.city = company.getCity();
        this.postcode = company.getPostcode();
        this.province = company.getProvince();
        this.country = company.getCountry();
        this.additionalFields = company.getAdditionalFields();
    }
}
