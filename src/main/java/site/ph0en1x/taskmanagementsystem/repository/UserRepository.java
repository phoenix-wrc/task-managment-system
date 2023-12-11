package site.ph0en1x.taskmanagementsystem.repository;

import site.ph0en1x.taskmanagementsystem.model.entity.user.Role;
import site.ph0en1x.taskmanagementsystem.model.entity.user.User;

import java.util.Optional;

//@Mapper
public interface UserRepository {
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    void update(User user);

    void create(User user);

    void insertUserRole(
//            @Param("userId")
            Long userId,
//            @Param("role")
            Role role);

    boolean isTaskOwner(
//            @Param("userId")
            Long userId,
//            @Param("taskId")
            Long taskId);

    boolean isTaskExecutor(
//            @Param("userId")
            Long userId,
//            @Param("taskId")
            Long taskId);

    void delete(Long id);
}
