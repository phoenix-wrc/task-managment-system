package site.ph0en1x.taskmanagementsystem.service.Impl;

import org.springframework.stereotype.Service;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Priority;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Status;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Task;
import site.ph0en1x.taskmanagementsystem.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Override
    public Task getById(Long id) {
        return null;
    }

    @Override
    public List<Task> getAllByUserId(Long userId) {
        return null;
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public Task create(Task task, Long authorId) {
        return null;
    }

    @Override
    public void appointToExecutorById(Long taskId, Long userId) {

    }

    @Override
    public void setStatusById(Long taskId, Status status) {

    }

    @Override
    public void setPriorityById(Long taskId, Priority priority) {

    }

    @Override
    public void delete(Long id) {

    }
}
