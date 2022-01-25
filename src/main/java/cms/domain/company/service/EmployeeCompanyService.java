package cms.domain.company.service;

import cms.api.company.employee.EmployeeCompanyReadModel;
import cms.api.company.employee.UpdateEmployeeRequest;
import cms.domain.company.repository.AgreementRepository;
import cms.domain.company.serviceImpl.EmployeeCompanyServiceImpl;
import cms.domain.employee.entity.Employee;
import cms.domain.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeCompanyService implements EmployeeCompanyServiceImpl {

    private final AgreementRepository agreementRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeCompanyService(AgreementRepository agreementRepository, EmployeeRepository employeeRepository) {
        this.agreementRepository = agreementRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeCompanyReadModel> readCompanyEmployees(String idClient) {
        return agreementRepository.findAllByCompanyAgreement_User_IdClient(idClient).stream().map(EmployeeCompanyReadModel::new).collect(Collectors.toList());
    }


    public EmployeeCompanyReadModel readEmployeeDetails(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        return new EmployeeCompanyReadModel(employee);
    }

    public EmployeeCompanyReadModel updateEmployee(Long id, UpdateEmployeeRequest updateEmployeeRequest) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        updateEmployeeRequest.updateEntity(employee);
        employeeRepository.save(employee);
        return new EmployeeCompanyReadModel(employee);
    }

}
