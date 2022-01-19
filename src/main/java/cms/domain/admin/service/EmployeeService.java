package cms.domain.admin.service;

import cms.api.admin.users.employee.EmployeeReadModel;
import cms.domain.admin.serviceImpl.EmployeeServiceImpl;
import cms.domain.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements EmployeeServiceImpl {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeReadModel> readAll() {
        return repository.findAll().stream().map(EmployeeReadModel::new).collect(Collectors.toList());
    }
}
