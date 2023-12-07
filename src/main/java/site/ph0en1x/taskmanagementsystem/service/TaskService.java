package site.ph0en1x.taskmanagementsystem.service;

import site.ph0en1x.taskmanagementsystem.model.task.Priority;
import site.ph0en1x.taskmanagementsystem.model.task.Status;
import site.ph0en1x.taskmanagementsystem.model.task.Task;

import java.util.List;

public interface TaskService {
    Task getById(Long id);

    List<Task> getAllByUserId(Long userId);

    Task update(Task task);

    Task create(Task task);

    void appointToExecutorById(Long taskId, Long userId);

    void setStatusById(Long taskId, Status status);

    void setPriorityById(Long taskId, Priority priority);

    void delete(Long id);
}
