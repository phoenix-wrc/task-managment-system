package site.ph0en1x.taskmanagementsystem.comment.entity;

import org.springframework.stereotype.Component;
import site.ph0en1x.taskmanagementsystem.comment.entity.dto.CommentDto;
import site.ph0en1x.taskmanagementsystem.task.entity.Task;
import site.ph0en1x.taskmanagementsystem.user.entity.User;
import site.ph0en1x.taskmanagementsystem.utils.Mapper;

import java.time.LocalDateTime;


@Component
public class CommentMapper implements Mapper<Comment, CommentDto> {

    @Override
    public CommentDto toDto(Comment comment) {

        if ( comment == null ) {
            return null;
        }

        return CommentDto.builder()
                .id(comment.getId())
                .title(comment.getTitle())
                .description(comment.getDescription())
                .dateCreate(comment.getDateCreate())
                .TaskId(comment.getTask().getId())
                .AuthorId(comment.getAuthor().getId())
                .build();
    }

    @Override
    public Comment toEntity(CommentDto dto) {

        if ( dto == null ) {
            return null;
        }

        Task task = new Task();
        task.setId(dto.getTaskId());
        User user = new User();
        user.setId(dto.getAuthorId());
        return Comment.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dateCreate(LocalDateTime.now())
                .author(user)
                .task(task)
                .build();
    }
}
