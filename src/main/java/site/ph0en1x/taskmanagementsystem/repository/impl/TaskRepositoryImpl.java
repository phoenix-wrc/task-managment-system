package site.ph0en1x.taskmanagementsystem.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.ph0en1x.taskmanagementsystem.model.task.Task;
import site.ph0en1x.taskmanagementsystem.repository.DataSourceConfig;
import site.ph0en1x.taskmanagementsystem.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {
    @Override
    public Optional<Task> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Task> findAllByUserId() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void create(Task task) {

    }

    @Override
    public void assignToUserById(Long taskId, Long userId) {

    }

    @Override
    public void update(Task task) {

    }
}
