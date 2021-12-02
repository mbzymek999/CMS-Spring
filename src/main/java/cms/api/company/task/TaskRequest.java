package cms.api.company.task;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {

    private String name;
    private String type;
    private String description;
    private LocalDate dateTo;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}
