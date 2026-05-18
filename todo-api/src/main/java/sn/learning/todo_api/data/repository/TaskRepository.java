package sn.learning.todo_api.data.repository;

import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.learning.todo_api.data.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @NullMarked
    Page<Task> findAll(Pageable pageable);
}
