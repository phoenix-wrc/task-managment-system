package site.ph0en1x.taskmanagementsystem.comment.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CommentDto {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime dateCreate;

    private Long AuthorId;

    private Long TaskId;
}
