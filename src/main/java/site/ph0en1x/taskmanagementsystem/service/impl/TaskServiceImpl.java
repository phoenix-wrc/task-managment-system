package site.ph0en1x.taskmanagementsystem.service.impl;

import org.springframework.stereotype.Service;
import site.ph0en1x.taskmanagementsystem.model.task.Task;
import site.ph0en1x.taskmanagementsystem.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Override
    public Task getById(Long id) {
        return null;
    }

    @Override
    public List<Task> getAllByUserId(Long id) {
        return null;
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public Task create(Task task, Long userId) {
        return null;
    }

    @Override
    public void delete(Long task) {

    }

    @Override
    public void assignToUserById(Long taskId, Long userId) {

    }
}