package cms.api.company.task;

import cms.domain.company.entity.Task;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class ReadCompanyTasksResponse {

    Long totalElements;
    int totalPages;
    int currentPage;
    List<TaskCompanyReadModel> tasks;

    public ReadCompanyTasksResponse(Page<Task> page, List<TaskCompanyReadModel> tasks) {
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber();
        this.tasks = tasks;
    }
}