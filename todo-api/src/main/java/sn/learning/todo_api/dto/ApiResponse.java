package sn.learning.todo_api.dto;

public record ApiResponse<T>(
        String message,
        int status,
        T data
) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("Success", 200, data);
    }
}
