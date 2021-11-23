package cms.api.company.dto;

import cms.domain.company.entity.Task;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskReadModel {
    private String name;
    private String type;
    private LocalDate dateTo;

    public TaskReadModel(Task task){
        this.name = task.getName();
        this.type = task.getType();
        this.dateTo = task.getDateTo();
    }
}
