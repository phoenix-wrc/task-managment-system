package site.ph0en1x.taskmanagementsystem.repository;

import site.ph0en1x.taskmanagementsystem.model.entity.task.Task;

import java.util.List;
import java.util.Optional;

//@Mapper
public interface TaskRepository {
    Optional<Task> findById(Long id);

    List<Task> findAllByUserId(Long userId);

    void appointToUserById(
//            @Param("task_id")
            Long taskId,
//            @Param("user_id")
            Long userId);

    void appointToExecutorById(
//            @Param("task_id")
            Long taskId,
//            @Param("user_id")
            Long userId);

    void update(Task task);

    void create(Task task);

    void delete(Long id);
}
