package cms.domain.admin.serviceImpl;

import cms.api.admin.users.employee.EmployeeReadModel;

import java.util.List;

public interface EmployeeServiceImpl {

    List<EmployeeReadModel> readAll();
}
