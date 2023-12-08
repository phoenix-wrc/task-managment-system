package site.ph0en1x.taskmanagementsystem.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.ph0en1x.taskmanagementsystem.model.entity.user.Role;
import site.ph0en1x.taskmanagementsystem.model.entity.user.User;
import site.ph0en1x.taskmanagementsystem.model.exception.ResourceMappingException;
import site.ph0en1x.taskmanagementsystem.repository.DataSourceConfig;
import site.ph0en1x.taskmanagementsystem.repository.UserRepository;
import site.ph0en1x.taskmanagementsystem.repository.mapper.UserRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final DataSourceConfig dataSourceConfig;

    private final String FIND_BY_ID = """
            select u.id              as user_id,
                   u.name            as user_name,
                   u.second_name     as user_second_name,
                   u.last_name       as user_last_name,
                   u.username        as user_username,
                   u.password        as user_password,
                   ur.role           as user_role,
                   t.id              as task_id,
                   t.title           as task_title,
                   t.description     as task_description,
                   t.expiration_date as task_expiration_date,
                   t.status          as task_status,
                   tp.priority_name  as task_priority,
                   te.user_id        as task_executor_id,
                   uto.user_id as task_owner_id
            from app_user u
                     LEFT JOIN user_role ur on u.id = ur.user_id
                     LEFT JOIN user_task_owner uto on u.id = uto.user_id
                     LEFT JOIN task t on t.id = uto.task_id
                     JOIN task_priority tp on tp.id = t.priority_id
                     join user_task_executor te on te.task_id = t.id
            where u.id = ?;
            """;

    private final String FIND_BY_USERNAME = """
            select u.id              as user_id,
                   u.name            as user_name,
                   u.second_name     as user_second_name,
                   u.last_name       as user_last_name,
                   u.username        as user_username,
                   u.password        as user_password,
                   ur.role           as user_role,
                   t.id              as task_id,
                   t.title           as task_title,
                   t.description     as task_description,
                   t.expiration_date as task_expiration_date,
                   t.status          as task_status,
                   tp.priority_name  as task_priority,
                   te.user_id        as task_executor_id,
                   uto.user_id as task_owner_id
            from app_user u
                     LEFT JOIN user_role ur on u.id = ur.user_id
                     LEFT JOIN user_task_owner uto on u.id = uto.user_id
                     LEFT JOIN task t on t.id = uto.task_id
                     JOIN task_priority tp on tp.id = t.priority_id
                     join user_task_executor te on te.task_id = t.id
            where u.username = ?;
            """;

    private final String UPDATE = """
            UPDATE app_user
            SET name = ?,
                second_name = ?,
                last_name = ?,
                username = ?,
                password = ?
            where id = ?
            """;

    private final String CREATE = """
            INSERT INTO app_user (name, second_name, last_name, username, password)
            VALUES (?,?,?,?,?);
            """;

    private final String DELETE = """
            DELETE FROM app_user
            WHERE id = ?;
            """;

    private final String INSERT_USER_ROLE = """
            INSERT INTO user_role (user_id, role)
            VALUES (?, ?);
            """;

    private final String IS_TASK_OWNER = """
            SELECT exists(SELECT 1
                          FROM user_task_owner
                          WHERE user_id = ?
                            AND task_id = ?)
            """;

    private final String IS_TASK_EXECUTOR = """
            SELECT exists(SELECT 1
                          FROM user_task_executor
                          WHERE user_id = ?
                            AND task_id = ?)
            """;
    @Override
    public Optional<User> findById(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                return Optional.ofNullable(UserRowMapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            throw new ResourceMappingException("Error while finding user by id");
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_USERNAME,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                return Optional.ofNullable(UserRowMapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            throw new ResourceMappingException("Error while finding user by username");
        }
    }

    @Override
    public void update(User user) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ResourceMappingException("Error while updating user");
        }
    }

    @Override
    public void create(User user) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());

            statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                rs.next();
                user.setId(rs.getLong(1));
            }
        } catch (SQLException ex) {
            throw new ResourceMappingException("Error while creating user");
        }
    }

    @Override
    public void insertUserRole(Long userId, Role role) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_ROLE);
            statement.setLong(1, userId);
            statement.setString(2, role.name());

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ResourceMappingException("Error while insert user role");
        }
    }

    @Override
    public boolean isTaskOwner(Long userId, Long taskId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(IS_TASK_OWNER);
            statement.setLong(1, userId);
            statement.setLong(2, taskId);

            try(ResultSet rs = statement.executeQuery()) {
                rs.next();
                return rs.getBoolean(1);
            }
        } catch (SQLException ex) {
            throw new ResourceMappingException("Error while checking is task owner");
        }
    }

    @Override
    public boolean isTaskExecutor(Long userId, Long taskId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(IS_TASK_EXECUTOR);
            statement.setLong(1, userId);
            statement.setLong(2, taskId);

            try(ResultSet rs = statement.executeQuery()) {
                rs.next();
                return rs.getBoolean(1);
            }
        } catch (SQLException ex) {
            throw new ResourceMappingException("Error while checking is task executor");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(IS_TASK_EXECUTOR);
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ResourceMappingException("Error while deleting user");
        }

    }
}
