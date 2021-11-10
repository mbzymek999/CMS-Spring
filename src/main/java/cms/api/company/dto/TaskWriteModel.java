package cms.api.company.dto;

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
    private LocalDate dateTo;
    private Long employeeId;

    public TaskWriteModel(Task task) {
        this.name = task.getName();
        this.type = task.getType();
        this.dateTo = task.getDateTo();
        this.employeeId = task.getEmployeeTask().getId();
    }

    public Task toTask(Employee employee) {
        Task task = new Task();
        task.setName(this.name);
        task.setType(this.type);
        task.setDateTo(this.dateTo);
        task.setEmployeeTask(employee);
        return task;
    }

}
