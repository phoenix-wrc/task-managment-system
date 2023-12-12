package site.ph0en1x.taskmanagementsystem.model.entity.task;

import jakarta.persistence.*;
import lombok.Data;
import site.ph0en1x.taskmanagementsystem.model.entity.comment.Comment;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "task")
@Data
@SecondaryTable(name = "task_priority",
        pkJoinColumns=@PrimaryKeyJoinColumn(name="task_id", referencedColumnName="id"))
@SecondaryTable(name = "user_task_owner",
        pkJoinColumns=@PrimaryKeyJoinColumn(name="task_id", referencedColumnName="id"))
@SecondaryTable(name = "user_task_executor",
        pkJoinColumns=@PrimaryKeyJoinColumn(name="task_id", referencedColumnName="id"))
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;


    private String description;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Enumerated(value = EnumType.STRING)
    @Column(table="task_priority", name="priority")
    private Priority priority;

    @Column(table="user_task_owner", name="author_id")
    private Long authorId;

    @Column(table="user_task_executor", name="executor_id")
    private Long executorId;

    private LocalDateTime createDate;

    private LocalDateTime expirationDate;

    @OneToMany
    private List<Comment> comments;
}
