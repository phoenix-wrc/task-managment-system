package site.ph0en1x.taskmanagementsystem.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.ph0en1x.taskmanagementsystem.task.entity.Priority;
import site.ph0en1x.taskmanagementsystem.task.entity.Status;
import site.ph0en1x.taskmanagementsystem.task.entity.Task;
import site.ph0en1x.taskmanagementsystem.user.entity.User;
import site.ph0en1x.taskmanagementsystem.security.exception.ResourceNotFoundException;
import site.ph0en1x.taskmanagementsystem.task.TaskRepository;
import site.ph0en1x.taskmanagementsystem.user.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public Task getById(Long id) {
        var out = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
//        log.debug(, out.getAuthorId(), t);
        return out;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByUserId(Long userId) {
        return taskRepository.findAllByAuthorId(userId);
    }

    @Override
    @Transactional
    public Task update(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(Status.AWAITING);
        }
        taskRepository.save(task);
        return task;
    }

    @Override
    @Transactional
    public Task create(Task task, Long authorId) {
        User user = userService.getById(authorId);
        task.setStatus(Status.AWAITING);
        if (task.getPriority() == null) {
            task.setPriority(Priority.LOW);
        }
        user.getTasksOwn().add(task);
        taskRepository.save(task);
        userService.update(user);
        return task;
    }

//    @Override
//    @Transactional
//    public void appointToExecutorById(Long taskId, Long userId) {
//        taskRepository.appointToExecutorById(taskId, userId);
//
//    }

    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
