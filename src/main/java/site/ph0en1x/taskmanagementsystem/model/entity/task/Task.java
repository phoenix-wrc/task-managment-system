package site.ph0en1x.taskmanagementsystem.model.entity.task;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;
import site.ph0en1x.taskmanagementsystem.model.entity.comment.Comment;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "task")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;


    private String description;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Enumerated(value = EnumType.ORDINAL)
    private Priority priority;

//    private Long authorId;
//
//    private Long executorId;

    private LocalDateTime createDate;

    private LocalDateTime expirationDate;

    
    private List<Comment> comments;
}
