package cms.api.company.task;

import cms.domain.company.entity.Task;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskCompanyReadModel {

    private final Long idCompany;
    private final String employeeName;
    private final String employeeLastName;
    private final String name;
    private final String type;
    private final int statusTask;
    private final String description;
    private final LocalDate createdDate;
    private final LocalDate dateTo;

    public TaskCompanyReadModel(Task task) {
        this.idCompany = task.getCompanyTask().getId();
        this.employeeName = task.getEmployeeTask().getName();
        this.employeeLastName = task.getEmployeeTask().getLastName();
        this.name = task.getName();
        this.type = task.getType();
        this.statusTask = task.getStatusTask();
        this.description = task.getDescription();
        this.createdDate = task.getCreatedDate();
        this.dateTo = task.getDateTo();
    }
}
