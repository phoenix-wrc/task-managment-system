package site.ph0en1x.taskmanagementsystem.repository;

import site.ph0en1x.taskmanagementsystem.model.task.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository
//        extends JpaRepository<Task, Long>
{
    Optional<Task> findById(Long id);

    List<Task> findAllByUserId();

    void assignToUserById(Long id, Long userId);

    void delete(Long id);

    void create(Task task);


    void update(Task task);
}
