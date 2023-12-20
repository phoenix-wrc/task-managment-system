package site.ph0en1x.taskmanagementsystem.task.service;

import site.ph0en1x.taskmanagementsystem.task.entity.Task;

import java.util.List;

public interface TaskService {
    Task getById(Long id);

    List<Task> getAllByUserId(Long userId);

    Task update(Task task);

    Task create(Task task, Long authorId);

//    void appointToExecutorById(Long taskId, Long userId);

    void delete(Long id);

    List<Task> getAllExecuteById(Long id);
}
