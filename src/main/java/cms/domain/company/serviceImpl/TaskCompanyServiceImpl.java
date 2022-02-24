package cms.domain.company.serviceImpl;

import cms.api.company.task.ReadCompanyTasksResponse;
import cms.api.company.task.TaskRequest;
import org.springframework.data.domain.Pageable;

public interface TaskCompanyServiceImpl {

    String addNewTask(TaskRequest taskRequest, Long employeeId, String idClient);

    ReadCompanyTasksResponse readCompanyTasks(Pageable page, String idClient);

    ReadCompanyTasksResponse readAllWithStatusTask(Pageable page, String idClient, int statusTask);

}
