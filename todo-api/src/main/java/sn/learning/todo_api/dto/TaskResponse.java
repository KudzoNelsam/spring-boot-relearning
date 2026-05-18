package sn.learning.todo_api.dto;

public record TaskResponse(
        Long id,
        String title,
        String description,
        String status
) {
}
