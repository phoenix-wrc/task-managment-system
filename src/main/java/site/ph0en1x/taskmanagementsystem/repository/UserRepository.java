package site.ph0en1x.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.ph0en1x.taskmanagementsystem.model.user.Role;
import site.ph0en1x.taskmanagementsystem.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    void insertUserRole(Long userId, Role role);
    boolean isTaskOwner(Long userId, Long taskId);

    boolean isTaskExecutor(Long userId, Long taskId);
}
