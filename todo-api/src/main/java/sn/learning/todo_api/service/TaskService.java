package sn.learning.todo_api.service;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.learning.todo_api.data.entities.Task;
import sn.learning.todo_api.data.enums.Status;
import sn.learning.todo_api.data.repository.TaskRepository;
import sn.learning.todo_api.dto.TaskRequest;
import sn.learning.todo_api.exception.TaskAlreadyCompleteException;
import sn.learning.todo_api.exception.TaskNotFoundException;
import sn.learning.todo_api.mapper.TaskMapper;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public void createTask(TaskRequest request) {
        Task task = taskMapper.toDomain(request);
        task.setStatus(Status.ACTIVE);
        taskRepository.save(task);
    }

    public Page<Task> getAllTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findAll(pageable);
    }

    public void finishTask(Long id) {
        Task task = getTask(id);
        if (task.getStatus() == Status.COMPLETED) {
            throw new TaskAlreadyCompleteException(id);
        }
        taskRepository.save(task);
    }

    private @NonNull Task getTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        return task;
    }

    public void deleteTask(Long id) {
        Task task = getTask(id);
        taskRepository.delete(task);
    }
}
