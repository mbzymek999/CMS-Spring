package cms.api.employee.task;

import cms.domain.company.entity.Task;
import lombok.Data;

@Data
public class UpdateTaskResponse {

    private int statusTask;

    public void updateEntity(Task task){
        task.setStatusTask(this.statusTask);
    }

}
