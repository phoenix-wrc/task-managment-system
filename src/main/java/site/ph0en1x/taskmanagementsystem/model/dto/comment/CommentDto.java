package site.ph0en1x.taskmanagementsystem.model.dto.comment;

import site.ph0en1x.taskmanagementsystem.model.entity.user.User;

import java.time.LocalDateTime;

public class CommentDto {
    private Long id;
    private Long taskId;

    private User userId;

    private String title;

    private String description;

    private LocalDateTime dateCreate;
}
