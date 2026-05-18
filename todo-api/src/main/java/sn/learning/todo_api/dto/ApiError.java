package sn.learning.todo_api.dto;

public record ApiError(
        int status,
        String message
) {
}
