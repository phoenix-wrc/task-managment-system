package site.ph0en1x.taskmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.ph0en1x.taskmanagementsystem.exception.ResourceNotFoundException;
import site.ph0en1x.taskmanagementsystem.model.task.Status;
import site.ph0en1x.taskmanagementsystem.model.task.Task;
import site.ph0en1x.taskmanagementsystem.repository.TaskRepository;
import site.ph0en1x.taskmanagementsystem.repository.UserRepository;
import site.ph0en1x.taskmanagementsystem.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repo;

    @Override
    @Transactional(readOnly = true)
    public Task getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByUserId(Long id) {
        return repo.findAllByUserId();
    }

    @Override
    @Transactional
    public Task update(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(Status.AWAITING);
        }
        repo.update(task);
        return task;
    }

    @Override
    @Transactional
    public Task create(Task task, Long userId) {
        task.setStatus(Status.AWAITING);
        repo.create(task);
        repo.assignToUserById(task.getId(), userId);
        return task;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repo.delete(id);
    }
}
