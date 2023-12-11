package site.ph0en1x.taskmanagementsystem.repository.mapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Task;
import site.ph0en1x.taskmanagementsystem.model.entity.user.Role;
import site.ph0en1x.taskmanagementsystem.model.entity.user.User;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class UserRowMapper {
    @SneakyThrows
    public static User mapRow(ResultSet rs) {
        Set<Role> roles = new HashSet<>();
        while (rs.next()) {
            roles.add(Role.valueOf(rs.getString("user_role")));
        }
        rs.beforeFirst();
        List<Task> tasks = TaskRowMapper.mapRows(rs);
        rs.beforeFirst();
        if (rs.next()) {
            User user = new User();
            user.setId(rs.getLong("user_id"));
            user.setName(rs.getString("user_name"));
            user.setSecondName(rs.getString("user_second_name"));
            user.setLastName(rs.getString("user_last_name"));
            user.setUsername(rs.getString("user_username"));
            user.setPassword(rs.getString("user_password"));
            user.setRoles(roles);
            user.setTasksOwn(tasks);
            log.debug("Create user: {}, {}, {}, {}, {}",
                    user.getId(),
                    user.getName(),
                    user.getSecondName(),
                    user.getLastName(),
                    user.getUsername());
            return user;
        }
        return null;
    }
}
