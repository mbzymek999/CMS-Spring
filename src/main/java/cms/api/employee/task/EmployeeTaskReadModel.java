package cms.api.employee.task;

import cms.domain.company.entity.Task;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeTaskReadModel {
    private Long idEmployee;
    private int idTask;
    private String name;
    private String description;
    private String type;
    private LocalDate dateTo;
    private LocalDate createdDate;
    private int statusTask;

    public EmployeeTaskReadModel(Task task) {
        this.idEmployee = task.getEmployeeTask().getId();
        this.idTask = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.type = task.getType();
        this.dateTo = task.getDateTo();
        this.createdDate = task.getCreatedDate();
        this.statusTask = task.getStatusTask();
    }
}
