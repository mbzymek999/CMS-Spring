package cms.domain.company.serviceImpl;

import cms.api.company.task.ReadCompanyTasksResponse;
import cms.api.company.task.TaskRequest;
import cms.domain.company.entity.Company;
import cms.domain.company.entity.Task;
import cms.domain.employee.entity.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskCompanyServiceImpl {

    String addNewTask(TaskRequest taskRequest, Long employeeId, String idClient);

    ReadCompanyTasksResponse readCompanyTasks(Pageable page, String idClient);

    ReadCompanyTasksResponse readByStatusTask(Pageable page, String idClient, int statusTask);

    ReadCompanyTasksResponse readAllWithStatusTask(Pageable page, String idClient, int statusTask);

    List<Task> getAllTasks(String idClient);

    void checkIfCompanyExist(Company company);

    void checkIfEmployeeExist(Employee employee);
}
