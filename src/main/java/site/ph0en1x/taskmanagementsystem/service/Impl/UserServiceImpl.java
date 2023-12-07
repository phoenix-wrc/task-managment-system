package site.ph0en1x.taskmanagementsystem.service.Impl;

import org.springframework.stereotype.Service;
import site.ph0en1x.taskmanagementsystem.model.entity.user.User;
import site.ph0en1x.taskmanagementsystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public User getByUserName(String username) {
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
