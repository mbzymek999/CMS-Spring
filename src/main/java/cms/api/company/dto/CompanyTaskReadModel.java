package cms.api.company.dto;

import cms.domain.company.entity.Task;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyTaskReadModel {

    private Long idCompany;
    private String name;
    private String type;
    private LocalDate dateTo;

    public CompanyTaskReadModel (Task task) {
        this.idCompany = task.getCompanyTask().getId();
        this.name = task.getName();
        this.type = task.getType();
        this.dateTo = task.getDateTo();
    }
}
