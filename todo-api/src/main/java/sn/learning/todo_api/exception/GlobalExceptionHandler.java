package sn.learning.todo_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sn.learning.todo_api.dto.ApiError;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<?> handleTaskNotFoundException(Exception e) {
        ApiError error = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()// 2. Dynamique (ou laissez "Task not found" si vous préférez)
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // 3. Définit le statut HTTP à 404
                .body(error);
    }


    @ExceptionHandler(TaskAlreadyCompleteException.class)
    public ResponseEntity<?> handleTaskAlreadyCompletedException(Exception e) {
        ApiError error = new ApiError(
                HttpStatus.CONFLICT.value(),
                e.getMessage()//
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }
}
