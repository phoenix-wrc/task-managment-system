package site.ph0en1x.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = """
    SELECT t.id              as id,
           t.title           as title,
           t.description     as description,
           t.expiration_date as expiration_date,
           t.create_date     as create_date,
           t.status          as status,
           uto.user_id       as author_id,
           te.user_id        as executor_id,
           tp.priority_name  as priority
    from TASK t
             join user_task_owner ut on t.id = ut.task_id
             join task_priority tp on tp.id = t.priority_id
             join user_task_executor te on te.task_id = t.id
             join user_task_owner uto on t.id = uto.task_id
    where ut.user_id = :userId
            """)
    List<Task> findAllByAuthorId(@Param("userId") Long userId);

    void appointToUserById(Long taskId, Long userId);

    void appointToExecutorById(Long taskId, Long userId);
}
