package sn.learning.todo_api.mapper;

import org.springframework.stereotype.Component;
import sn.learning.todo_api.data.entities.Task;
import sn.learning.todo_api.dto.TaskRequest;
import sn.learning.todo_api.dto.TaskResponse;

@Component
public class TaskMapper {
    public Task toDomain(TaskRequest request) {
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .build();
    }

    public TaskResponse toDto(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().name());
    }
}
