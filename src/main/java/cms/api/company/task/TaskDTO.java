package cms.api.company.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long idCompany;
    private String employeeName;
    private String name;
    private String type;
    private String description;
    private LocalDate createdDate;
    private LocalDate dateTo;
}
