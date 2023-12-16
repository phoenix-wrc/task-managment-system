package site.ph0en1x.taskmanagementsystem.comment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long taskId;

    private Long userId;

    private String title;

    private String description;

    private LocalDateTime dateCreate;
}
