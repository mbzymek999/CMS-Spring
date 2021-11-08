package domain.employee.service;

import api.employee.response.EmployeeReadModel;
import domain.employee.repositoriy.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeReadModel> readAll() {
        return repository.findAll().stream().map(EmployeeReadModel::new).collect(Collectors.toList());
    }

}
