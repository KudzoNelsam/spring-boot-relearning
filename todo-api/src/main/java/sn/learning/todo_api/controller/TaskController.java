package sn.learning.todo_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.learning.todo_api.dto.ApiPaginatedResponse;
import sn.learning.todo_api.dto.ApiResponse;
import sn.learning.todo_api.dto.TaskRequest;
import sn.learning.todo_api.dto.TaskResponse;
import sn.learning.todo_api.mapper.TaskMapper;
import sn.learning.todo_api.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;


    @PostMapping()
    public ResponseEntity<ApiResponse<String>> createTask(@RequestBody TaskRequest request) {
        taskService.createTask(request);
        return ResponseEntity.ok(ApiResponse.success("Task created successfully"));
    }

    @GetMapping()
    public ResponseEntity<ApiPaginatedResponse<List<TaskResponse>>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var pageTask = taskService.getAllTasks(page, size);
        var tasks = pageTask.getContent().stream().map(taskMapper::toDto).toList();


        return ResponseEntity.ok(
                new ApiPaginatedResponse<List<TaskResponse>>(
                        "Task loaded Successfully",
                        HttpStatus.OK.value(),
                        tasks,
                        page,
                        size,
                        pageTask.hasPrevious(),
                        pageTask.hasNext(),
                        new int[pageTask.getTotalPages()],
                        pageTask.getTotalPages()

                )
        );
    }

    @PatchMapping("/{id}/finish")
    public ResponseEntity<ApiResponse<String>> finishTask(@PathVariable Long id) {
        taskService.finishTask(id);
        return ResponseEntity.ok(ApiResponse.success("Task finished successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(ApiResponse.success("Task deleted successfully"));
    }
}
