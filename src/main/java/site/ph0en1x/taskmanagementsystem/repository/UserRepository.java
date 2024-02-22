package site.ph0en1x.taskmanagementsystem.repository;

import site.ph0en1x.taskmanagementsystem.model.user.Role;
import site.ph0en1x.taskmanagementsystem.model.user.User;

import java.util.Optional;

public interface UserRepository
//        extends JpaRepository<User, Long>
{
    void insertUserRole(Long userId, Role role);
    boolean isTaskOwner(Long userId, Long taskId);

    boolean isTaskExecutor(Long userId, Long taskId);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    void update(User user);

    void create(User user);

    void delete(Long id);
}
