package site.ph0en1x.taskmanagementsystem.task.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import site.ph0en1x.taskmanagementsystem.comment.entity.Comment;
import site.ph0en1x.taskmanagementsystem.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@org.springframework.data.relational.core.mapping.Table
@Data
@Builder
@AllArgsConstructor
@SecondaryTable(name = "task_priority",
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

    @OneToOne
    @JoinColumn(table="user", name="id")
    private User author;

    @OneToOne
    @JoinColumn(table="user", name="id")
    private User executor;

    private LocalDateTime createDate;

    private LocalDateTime expirationDate;

    @OneToMany
    private List<Comment> comments;

    public Task() {    }
}
