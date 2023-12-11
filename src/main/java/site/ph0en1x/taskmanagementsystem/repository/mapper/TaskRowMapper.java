package site.ph0en1x.taskmanagementsystem.repository.mapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Priority;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Status;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Task;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TaskRowMapper {

    @SneakyThrows
    public static Task mapRow(ResultSet rs) {
        if(rs.next()) {
            Task task = new Task();
            task.setId(rs.getLong("task_id"));
            task.setTitle(rs.getString("task_title"));
            task.setDescription(rs.getString("task_description"));
            Timestamp timestamp = rs.getTimestamp("task_expiration_date");
            if (timestamp != null) {
                task.setExpirationDate(timestamp.toLocalDateTime());
            }
            task.setCreateDate(rs.getTimestamp("task_create_date").toLocalDateTime());
            task.setStatus(Status.valueOf(rs.getString("task_status")));
            task.setPriority(Priority.valueOf(rs.getString("task_priority")));
            task.setExecutorId(rs.getLong("task_executor_id"));
            task.setAuthor(rs.getLong("task_author_id"));
            return task;
        }
        return null;
    }


    @SneakyThrows
    public static List<Task> mapRows(ResultSet rs) {
        List<Task> list = new ArrayList<>(rs.getFetchSize());
        while (rs.next()) {
            Task task = new Task();
            task.setId(rs.getLong("task_id"));
            if (!rs.wasNull()) {
                task.setTitle(rs.getString("task_title"));
                task.setDescription(rs.getString("task_description"));
                Timestamp timestamp = rs.getTimestamp("task_expiration_date");
                if (timestamp != null) {
                    task.setExpirationDate(timestamp.toLocalDateTime());
                }
                task.setCreateDate(rs.getTimestamp("task_create_date").toLocalDateTime());
                task.setStatus(Status.valueOf(rs.getString("task_status")));
                task.setPriority(Priority.valueOf(rs.getString("task_priority")));
                task.setExecutorId(rs.getLong("task_executor_id"));
                task.setAuthor(rs.getLong("task_author_id"));
                list.add(task);
            }
        }
        log.debug("Create list of task");
        return list;
    }
}
