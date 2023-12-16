package site.ph0en1x.taskmanagementsystem.task.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import site.ph0en1x.taskmanagementsystem.comment.entity.Comment;
import site.ph0en1x.taskmanagementsystem.auth.validation.OnCreate;
import site.ph0en1x.taskmanagementsystem.auth.validation.OnUpdate;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TaskDto {

    @NotNull(message = "ID must be not null", groups = {OnUpdate.class})
    private Long id;

    @NotNull(message = "Title must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Title length must be smaller than 255 characters", groups = {OnUpdate.class, OnCreate.class})
    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private Long author;

    private Long executorId;

    @DateTimeFormat (iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;

    @DateTimeFormat (iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expirationDate;

    private List<Comment> comments;
}
