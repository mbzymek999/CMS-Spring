package api.owner.request;

import api.owner.dto.TaskWriteModel;
import lombok.Data;

@Data
public class TaskRequest {
    private TaskWriteModel task;

    public TaskWriteModel getTask() {
        return task;
    }

    public void setTask(TaskWriteModel task) {
        this.task = task;
    }
}
