package site.ph0en1x.taskmanagementsystem.comment.dto;

import site.ph0en1x.taskmanagementsystem.user.entity.User;

import java.time.LocalDateTime;

public class CommentDto {
    private Long id;
    private Long taskId;

    private User userId;

    private String title;

    private String description;

    private LocalDateTime dateCreate;
}
