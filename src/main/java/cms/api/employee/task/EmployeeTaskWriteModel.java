package cms.api.employee.task;

import cms.domain.company.entity.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeTaskWriteModel {
    private int statusTask;

    public EmployeeTaskWriteModel(Task task) {
        this.statusTask = task.getStatusTask();
    }

    public void updateEntity(Task task){
        task.setStatusTask(this.statusTask);
    }
}
