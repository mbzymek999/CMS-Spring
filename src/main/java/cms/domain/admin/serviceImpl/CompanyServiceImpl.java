package cms.domain.admin.serviceImpl;

import cms.api.admin.users.company.CompanyReadModel;

import java.util.List;

public interface CompanyServiceImpl {

    List<CompanyReadModel> readAll();

    List<CompanyReadModel> readAllWithNipOrCompanyName(String nip, String companyName);
}
