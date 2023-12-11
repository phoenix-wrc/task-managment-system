package site.ph0en1x.taskmanagementsystem.model.entity.comment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long id;
    private Long taskId;
    private Long userId;
    private String title;
    private String description;
    private LocalDateTime dateCreate;
}
