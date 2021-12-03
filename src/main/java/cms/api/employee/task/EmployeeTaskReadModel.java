package cms.api.employee.task;

import cms.domain.company.entity.Task;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeTaskReadModel {
    private Long idEmployee;
    private String name;
    private String description;
    private String type;
    private LocalDate dateTo;
    private LocalDate createdDate;
    private boolean isAccepted;

    public EmployeeTaskReadModel(Task task) {
        this.idEmployee = task.getEmployeeTask().getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.type = task.getType();
        this.dateTo = task.getDateTo();
        this.createdDate = task.getCreatedDate();
        this.isAccepted = task.isAccepted();
    }
}
