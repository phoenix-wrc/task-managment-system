package site.ph0en1x.taskmanagementsystem.service.impl;

import org.springframework.stereotype.Service;
import site.ph0en1x.taskmanagementsystem.model.user.User;
import site.ph0en1x.taskmanagementsystem.repository.UserRepository;
import site.ph0en1x.taskmanagementsystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repo;
    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User isTaskOwner(Long userId, Long taskId) {
        return null;
    }

    @Override
    public User isTaskExecutor(Long userId, Long taskId) {
        return null;
    }
}
