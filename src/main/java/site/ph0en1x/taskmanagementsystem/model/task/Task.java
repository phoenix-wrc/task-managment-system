package site.ph0en1x.taskmanagementsystem.model.task;

import lombok.Data;
import site.ph0en1x.taskmanagementsystem.model.user.User;

import java.time.LocalDateTime;

@Data
public class Task {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDateTime createDate;
    private LocalDateTime expirationDate;
}