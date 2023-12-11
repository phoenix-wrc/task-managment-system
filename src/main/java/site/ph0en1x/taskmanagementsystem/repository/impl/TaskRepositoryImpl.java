package site.ph0en1x.taskmanagementsystem.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Task;
import site.ph0en1x.taskmanagementsystem.model.exception.ResourceMappingException;
import site.ph0en1x.taskmanagementsystem.repository.DataSourceConfig;
import site.ph0en1x.taskmanagementsystem.repository.TaskRepository;
import site.ph0en1x.taskmanagementsystem.repository.mapper.TaskRowMapper;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TaskRepositoryImpl implements TaskRepository {
    private final DataSourceConfig dataSourceConfig;
    private final String FIND_BY_ID = """
            select t.id              as task_id,
                   t.title           as task_title,
                   t.description     as task_description,
                   uto.user_id       as task_author_id,
                   t.expiration_date as task_expiration_date,
                   t.create_date     as task_create_date,
                   t.status          as task_status,
                   tp.priority_name  as task_priority,
                   te.user_id        as task_executor_id
            from task t
                     join task_priority tp on tp.id = t.priority_id
                     join user_task_executor te on te.task_id = t.id
                     join user_task_owner uto on t.id = uto.task_id
            where t.id = ?;""";
    private final String FIND_ALL_BY_USER_ID = """
            select t.id              as task_id,
                   t.title           as task_title,
                   t.description     as task_description,
                   t.expiration_date as task_expiration_date,
                   t.create_date     as task_create_date,
                   t.status          as task_status,
                   uto.user_id       as task_author_id,
                   te.user_id        as task_executor_id,    
                   tp.priority_name  as task_priority
                   
            from task t
                     join user_task_owner ut on t.id = ut.task_id
                     join task_priority tp on tp.id = t.priority_id
                     join user_task_executor te on te.task_id = t.id
                     join user_task_owner uto on t.id = uto.task_id
            where ut.user_id = ?
            """;
    private final String ASSIGN = """
            INSERT INTO user_task_owner (task_id, user_id)
            VALUES (?,?);
            """;

    private final String ASSIGN_TO_EXECUTOR = """
            INSERT INTO user_task_executor (task_id, user_id)
            VALUES (?,?);
            """;
    private final String DELETE = """
            DELETE FROM task
            WHERE id = ?;
            """;
    private final String CREATE = """
            INSERT INTO task (title,
                              description,
                              expiration_date,
                              status,
                              priority_id)
            VALUES (?,
                    ?,
                    ?,
                    ?,
                    (select tp.id
                     from task_priority tp
                     where tp.priority_name = ?));
            """;
    private final String UPDATE = """
            UPDATE task
            SET title           = ?,
                description     = ?,
                expiration_date = ?,
                status          = ?,
                priority_id        = (select tp.id
                                   from task_priority tp
                                   where tp.priority_name = ?)
            WHERE id = ?;
            """;

    @Override
    public Optional<Task> findById(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                return Optional.ofNullable(TaskRowMapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            throw new ResourceMappingException("Error while finding task by id");
        }
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_USER_ID);
            statement.setLong(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                return TaskRowMapper.mapRows(rs);
            }
        } catch (SQLException ex) {
            log.debug(ex.getMessage());
            throw new ResourceMappingException("Error while finding all task by user id");
        }
    }

    @Override
    public void appointToUserById(Long taskId, Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(ASSIGN);
            statement.setLong(1, taskId);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ResourceMappingException("Error while appointing task to user by id");
        }
    }

    @Override
    public void appointToExecutorById(Long taskId, Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(ASSIGN_TO_EXECUTOR);
            statement.setLong(1, taskId);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ResourceMappingException("Error while appointing executor task to user by id");
        }

    }

    @Override
    public void update(Task task) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, task.getTitle());
            if (task.getDescription() != null) {
                statement.setString(2, task.getDescription());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }
            if (task.getExpirationDate() != null) {
                statement.setTimestamp(3, Timestamp.valueOf(task.getExpirationDate()));
            } else {
                statement.setNull(3, Types.TIMESTAMP);
            }
            statement.setString(4, task.getStatus().toString());
            statement.setString(5, task.getPriority().toString());
            statement.setLong(6, task.getId());

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ResourceMappingException("Error while update task");
        }
    }

    @Override
    public void create(Task task) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getTitle());
            if (task.getDescription() != null) {
                statement.setString(2, task.getDescription());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }
            if (task.getExpirationDate() != null) {
                statement.setTimestamp(3, Timestamp.valueOf(task.getExpirationDate()));
            } else {
                statement.setNull(3, Types.TIMESTAMP);
            }
            statement.setString(4, task.getStatus().toString());
            statement.setString(5, task.getPriority().toString());

            statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                rs.next();
                task.setId(rs.getLong(1));
            }
        } catch (SQLException ex) {
            throw new ResourceMappingException("Error while creating task");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new ResourceMappingException("Error while deleting task");
        }

    }
}
