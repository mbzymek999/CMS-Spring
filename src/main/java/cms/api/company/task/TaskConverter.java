package cms.api.company.task;

import cms.domain.company.entity.Task;

public class TaskConverter {
    public static TaskDTO toDTO(Task entity){
        return new TaskDTO(
                entity.getCompanyTask().getId(),
                entity.getEmployeeTask().getName(),
                entity.getName(),
                entity.getType(),
                entity.getDescription(),
                entity.getCreatedDate(),
                entity.getDateTo());
    }
}
