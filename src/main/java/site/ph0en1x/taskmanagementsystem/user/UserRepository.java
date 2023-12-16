package site.ph0en1x.taskmanagementsystem.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.ph0en1x.taskmanagementsystem.user.entity.User;

import java.util.Optional;

//@Mapper
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value = """
            SELECT exists(SELECT *
              FROM "task-management-system".user_task_owner
              WHERE user_id = :userId
                AND task_id = :taskId)
            """, nativeQuery = true)
    boolean isTaskOwner(@Param("userId") Long userId, @Param("taskId") Long taskId);

    @Query(value = """
            SELECT exists(SELECT *
              FROM "task-management-system".user_task_executor
              WHERE user_id = :userId
                AND task_id = :taskId)
            """, nativeQuery = true)
    boolean isTaskExecutor(@Param("userId") Long userId, @Param("taskId") Long taskId);
}
