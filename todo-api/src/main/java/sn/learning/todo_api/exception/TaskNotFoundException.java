package sn.learning.todo_api.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(Long id) {
        super(
                String.format("Task with id %s not found", id)
        );
    }
}
