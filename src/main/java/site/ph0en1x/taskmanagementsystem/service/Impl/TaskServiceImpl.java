package site.ph0en1x.taskmanagementsystem.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Priority;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Status;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Task;
import site.ph0en1x.taskmanagementsystem.model.exception.ResourceNotFoundException;
import site.ph0en1x.taskmanagementsystem.repository.TaskRepository;
import site.ph0en1x.taskmanagementsystem.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByUserId(Long userId) {
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Task update(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(Status.AWAITING);
        }
        if (task.getExecutorId() != null) {
            taskRepository.appointToExecutorById(task.getId(), task.getExecutorId());
        }
        taskRepository.update(task);
        return task;
    }

    @Override
    @Transactional
    public Task create(Task task, Long authorId) {
        task.setStatus(Status.AWAITING);
        if (task.getPriority() == null) {
            task.setPriority(Priority.LOW);
        }
        taskRepository.create(task);
        taskRepository.appointToUserById(task.getId(), authorId);
        return task;
    }

    @Override
    @Transactional
    public void appointToExecutorById(Long taskId, Long userId) {
        taskRepository.appointToExecutorById(taskId, userId);

    }

    @Override
    @Transactional
    public void setStatusById(Long taskId, Status status) {

    }

    @Override
    @Transactional
    public void setPriorityById(Long taskId, Priority priority) {

    }

    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.delete(id);
    }
}
