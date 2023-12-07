package site.ph0en1x.taskmanagementsystem.model.entity.user;

import lombok.Data;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Task;

import java.util.List;
import java.util.Set;

@Data
public class User {
    private Long id;
    private String name;
    private String secondName;
    private String lastName;
    private String username;
    private String password;
    private String passwordConfirmation;
    private Set<Role> roles;
    private List<Task> tasks;

}
