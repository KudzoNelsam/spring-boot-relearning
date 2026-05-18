package sn.learning.todo_api.dto;

public record ApiPaginatedResponse<T>(
        String message,
        int status,
        T data,
        int page,
        int size,
        boolean hasPrevious,
        boolean hasNext,
        int[] pages,
        int totalPage
) {

//    public static <T> ApiPaginatedResponse<List<T>> success(Page<List<T>> data) {
//        return new ApiPaginatedResponse<>(
//                "Success",
//                200,
//                data.getContent(),
//                data.getNumber(),
//                data.getSize(),
//                data.hasPrevious(),
//                data.hasNext(),
//                new int[data.getTotalPages()],
//                data.getTotalPages());
//    }
}
