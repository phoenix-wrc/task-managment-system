package site.ph0en1x.taskmanagementsystem.user.service;

import site.ph0en1x.taskmanagementsystem.user.entity.User;

public interface UserService {
    User getById(Long id);

    User getByUserName(String username);

    User update(User user);

    User create(User user);

    boolean isTaskOwner(Long userId, Long taskId);

    boolean isTaskExecutor(Long userId, Long taskId);

    void delete(Long id);
}
