package sn.learning.todo_api.seed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sn.learning.todo_api.data.entities.Task;
import sn.learning.todo_api.data.enums.Status;
import sn.learning.todo_api.data.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApplicationSeed implements CommandLineRunner {
    private final TaskRepository taskRepository;

    @Override
    public void run(String @NonNull ... args) throws Exception {
        if (taskRepository.findAll().isEmpty()) {
            List<Task> tasks = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                tasks.add(
                        Task.builder()
                            .title("Task " + i)
                                .status(Status.ACTIVE)
                            .description("Description " + i)
                            .build()
                );
            }
            taskRepository.saveAll(tasks);
            log.info("Task added successfully");
        }else {
            List<Task> tasks = taskRepository.findAll();
            for (Task task : tasks) {
                task.setStatus(Status.ACTIVE);
            }
            taskRepository.saveAll(tasks);
            log.info("Task already added");
        }
        taskRepository.flush();
    }
}
