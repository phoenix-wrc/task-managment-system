package site.ph0en1x.taskmanagementsystem.model.entity.task;

import lombok.Data;
import site.ph0en1x.taskmanagementsystem.model.entity.comment.Comment;
import site.ph0en1x.taskmanagementsystem.model.entity.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Task {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private Long author;
    private Long executorId;
    private LocalDateTime createDate;
    private LocalDateTime expirationDate;
    private List<Comment> comments;
}
