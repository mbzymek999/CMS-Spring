package cms.api.employee.controller;

import cms.api.employee.dto.EmployeeReadModel;
import cms.domain.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService service;
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    @RequestMapping("/employees")
    ResponseEntity<List<EmployeeReadModel>> readAllEmployees() {
        return ResponseEntity.ok(service.readAll());
    }
}
