package site.ph0en1x.taskmanagementsystem.repository.impl;

import org.springframework.stereotype.Repository;
import site.ph0en1x.taskmanagementsystem.model.user.Role;
import site.ph0en1x.taskmanagementsystem.model.user.User;
import site.ph0en1x.taskmanagementsystem.repository.UserRepository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void create(User user) {

    }

    @Override
    public void insertUserRole(Long userId, Role role) {

    }

    @Override
    public boolean isTaskOwner(Long userId, Long taskId) {
        return false;
    }

    @Override
    public boolean isTaskExecutor(Long userId, Long taskId) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }
}
