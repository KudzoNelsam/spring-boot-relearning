package sn.learning.todo_api.dto;

public record TaskRequest(
        String title,
        String description
) {
}
