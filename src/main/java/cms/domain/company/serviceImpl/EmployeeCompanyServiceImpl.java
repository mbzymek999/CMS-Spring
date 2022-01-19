package cms.domain.company.serviceImpl;

import cms.api.company.employee.EmployeeCompanyReadModel;
import cms.api.company.employee.UpdateEmployeeResponse;

import java.util.List;

public interface EmployeeCompanyServiceImpl {

    List<EmployeeCompanyReadModel> readCompanyEmployees(String idClient);

    EmployeeCompanyReadModel readEmployeeDetails(Long id);

    EmployeeCompanyReadModel updateEmployee(Long id, UpdateEmployeeResponse updateEmployeeResponse);

}
