package cms.api.company.task;

import cms.domain.company.entity.Task;
import cms.domain.employee.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TaskWriteModel {

    private String name;
    private String type;
    private String description;
    private LocalDate dateTo;

    public Task toTask() {
        Task task = new Task();
        task.setName(this.name);
        task.setType(this.type);
        task.setDescription(this.description);
        task.setAccepted(false);
        task.setCreatedDate(LocalDate.now());
        task.setDateTo(this.dateTo);
        return task;
    }

}
