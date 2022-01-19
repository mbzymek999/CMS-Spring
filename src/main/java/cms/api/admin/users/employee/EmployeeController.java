package cms.api.admin.users.employee;

import cms.domain.admin.serviceImpl.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @RequestMapping("/employees")
    List<EmployeeReadModel> readAllEmployees() {
        return employeeService.readAll();
    }
}
