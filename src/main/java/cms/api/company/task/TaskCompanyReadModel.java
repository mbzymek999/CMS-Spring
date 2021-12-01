package cms.api.company.task;

import cms.domain.company.entity.Task;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskCompanyReadModel {

    private Long idCompany;
    private String employeeName;
    private String name;
    private String type;
    private String description;
    private LocalDate createdDate;
    private LocalDate dateTo;

    public TaskCompanyReadModel(Task task) {
        this.idCompany = task.getCompanyTask().getId();
        this.employeeName = task.getEmployeeTask().getName();
        this.name = task.getName();
        this.type = task.getType();
        this.description = task.getDescription();
        this.createdDate = task.getCreatedDate();
        this.dateTo = task.getDateTo();
    }
}
