package cms.domain.company.serviceImpl;

import cms.api.company.employee.EmployeeCompanyReadModel;
import cms.api.company.employee.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeCompanyServiceImpl {

    List<EmployeeCompanyReadModel> readCompanyEmployees(String idClient);

    EmployeeCompanyReadModel readEmployeeDetails(Long id);

    EmployeeCompanyReadModel updateEmployee(Long id, UpdateEmployeeRequest updateEmployeeRequest);

}
