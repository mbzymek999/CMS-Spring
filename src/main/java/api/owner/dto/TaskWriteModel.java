package api.owner.dto;

import api.owner.entities.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TaskWriteModel {

    private String name;
    private String type;
    private LocalDate dateTo;

    public TaskWriteModel(Task task) {
        this.name = task.getName();
        this.type = task.getType();
        this.dateTo = task.getDateTo();
    }

    public Task toTask() {
        Task task = new Task();
        task.setName(this.name);
        task.setType(this.type);
        task.setDateTo(this.dateTo);
        return task;
    }

}
