package site.ph0en1x.taskmanagementsystem.user.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.*;
import site.ph0en1x.taskmanagementsystem.task.entity.Task;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_user")
@org.springframework.data.relational.core.mapping.Table
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
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
    @ElementCollection(fetch = FetchType.EAGER)
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

    public User() {    }
}
