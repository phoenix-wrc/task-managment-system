package site.ph0en1x.taskmanagementsystem.repository;

import site.ph0en1x.taskmanagementsystem.model.entity.task.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<Task> findById(Long id);

    List<Task> findAllByUserId(Long userId);

    void appointToUserById(Long taskId, Long userId);

    void appointToExecutorById(Long taskId, Long userId);

    void update(Task user);

    void create(Task user);

    void delete(Long id);
}
