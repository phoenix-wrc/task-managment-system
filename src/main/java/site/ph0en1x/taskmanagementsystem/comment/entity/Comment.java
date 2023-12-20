package site.ph0en1x.taskmanagementsystem.comment.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import site.ph0en1x.taskmanagementsystem.task.entity.Task;
import site.ph0en1x.taskmanagementsystem.user.entity.User;

import java.time.LocalDateTime;

@Entity
@jakarta.persistence.Table
@org.springframework.data.relational.core.mapping.Table
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime dateCreate;

    @ManyToOne
    @Lazy
    private User author;

    @ManyToOne
    @Lazy
    private Task task;

    public Comment() {

    }
}
