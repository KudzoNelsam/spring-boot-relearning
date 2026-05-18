package sn.learning.todo_api.exception;

public class TaskAlreadyCompleteException extends RuntimeException {
    public TaskAlreadyCompleteException(String message) {
        super(message);
    }

    public TaskAlreadyCompleteException(Long id) {
        super(
                String.format("Task with id %s is already completed", id)
        );
    }
}
