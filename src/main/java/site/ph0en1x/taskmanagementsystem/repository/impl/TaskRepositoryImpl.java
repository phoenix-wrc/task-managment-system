package site.ph0en1x.taskmanagementsystem.repository.impl;

import org.springframework.stereotype.Repository;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Task;
import site.ph0en1x.taskmanagementsystem.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    @Override
    public Optional<Task> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {
        return null;
    }

    @Override
    public void appointToUserById(Long taskId, Long userId) {

    }

    @Override
    public void appointToExecutorById(Long taskId, Long userId) {

    }

    @Override
    public void update(Task user) {

    }

    @Override
    public void create(Task user) {

    }

    @Override
    public void delete(Long id) {

    }
}
