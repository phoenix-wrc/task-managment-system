package site.ph0en1x.taskmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import site.ph0en1x.taskmanagementsystem.model.user.User;
import site.ph0en1x.taskmanagementsystem.repository.UserRepository;

public interface UserService {
    User getById(Long id);
    User getByUsername(String username);
    User update(User user);
    User create(User user);
    void delete(Long id);
    User isTaskOwner(Long userId, Long taskId);
    User isTaskExecutor(Long userId, Long taskId);
}
