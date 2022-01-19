package cms.domain.admin.serviceImpl;

import cms.api.admin.users.company.CompanyReadModel;

import java.util.List;

public interface CompanyServiceImpl {

    List<CompanyReadModel> readAll();

    List<CompanyReadModel> readByNipOrCompanyName(String nip, String companyName);

    List<CompanyReadModel> readByNip(String nip);

    List<CompanyReadModel> readByCompanyName(String companyName);

    List<CompanyReadModel> readAllWithNipOrCompanyName(String nip, String companyName);
}
