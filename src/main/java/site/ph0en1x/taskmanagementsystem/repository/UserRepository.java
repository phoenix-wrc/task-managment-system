package site.ph0en1x.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.ph0en1x.taskmanagementsystem.model.entity.user.Role;
import site.ph0en1x.taskmanagementsystem.model.entity.user.User;

import java.util.Optional;

//@Mapper
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value = """
            SELECT exists(SELECT 1
              FROM user_task_owner
              WHERE user_id = :userId
                AND task_id = :taskId)
            """)
    boolean isTaskOwner(@Param("userId") Long userId, @Param("taskId") Long taskId);

    @Query(value = """
            SELECT exists(SELECT 1
              FROM user_task_executor
              WHERE user_id = :userId
                AND task_id = :taskId)
            """)
    boolean isTaskExecutor(@Param("userId") Long userId, @Param("taskId") Long taskId);
}
