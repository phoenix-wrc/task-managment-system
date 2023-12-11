package site.ph0en1x.taskmanagementsystem.model.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Task;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String secondName;

    private String lastName;

    private String username;

    private String password;

    @Transient
    private String passwordConfirmation;

    @Column(name = "role")
    @ElementCollection
    @CollectionTable(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles;

    @CollectionTable(name = "user_task_owner")
    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Task> tasksOwn;

    @CollectionTable(name = "user_task_executor")
    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Task> tasksExecutor;
}
