package cms.api.company.task;

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
