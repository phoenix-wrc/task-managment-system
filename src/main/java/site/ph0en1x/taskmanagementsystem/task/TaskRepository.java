package site.ph0en1x.taskmanagementsystem.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import site.ph0en1x.taskmanagementsystem.task.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

//    @Query(value = """
//    SELECT t.id              as id,
//           t.title           as title,
//           t.description     as description,
//           t.expiration_date as expiration_date,
//           t.create_date     as create_date,
//           t.status          as status,
//           tp.priority       as priority
//    from "task-management-system".TASK t
//             join "task-management-system".user_task_owner ut on t.id = ut.task_id
//             join "task-management-system".task_priority tp on tp.task_id = t.id
//             join "task-management-system".user_task_executor te on te.task_id = t.id
//    where te.executor_id = :userId
//            """, nativeQuery = true)
    List<Task> findAllByExecutorId(@Param("userId") Long userId);

    //           uto.user_id       as author_id,
    //           te.user_id        as executor_id,
//    @Query(value = """
//    SELECT t.id              as id,
//           t.title           as title,
//           t.description     as description,
//           t.expiration_date as expiration_date,
//           t.create_date     as create_date,
//           t.status          as status,
//           tp.priority       as priority
//    from "task-management-system".TASK t
//             join "task-management-system".user_task_owner ut on t.id = ut.task_id
//             join "task-management-system".task_priority tp on tp.task_id = t.id
//             join "task-management-system".user_task_owner uto on t.id = uto.task_id
//    where uto.author_id = :userId
//            """, nativeQuery = true)
    List<Task> findAllByAuthorId(@Param("userId") Long userId);
}
