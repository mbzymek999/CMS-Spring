package cms.api.employee.task;

import cms.domain.company.entity.Task;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class EmployeeTaskResponse {
    Long totalElements;
    int totalPages;
    int currentPage;
    List<EmployeeTaskReadModel> tasks;

    public EmployeeTaskResponse(Page<Task> page, List<EmployeeTaskReadModel> tasks) {
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber();
        this.tasks = tasks;
    }
}
