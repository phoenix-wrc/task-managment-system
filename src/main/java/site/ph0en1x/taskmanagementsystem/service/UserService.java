package site.ph0en1x.taskmanagementsystem.service;

import site.ph0en1x.taskmanagementsystem.model.user.User;

public interface UserService {
    User getById(Long id);
    User getByUsername(String username);
    User update(User user);
    User create(User user);
    void delete(Long id);
    boolean isTaskOwner(Long userId, Long taskId);
    boolean isTaskExecutor(Long userId, Long taskId);
}
