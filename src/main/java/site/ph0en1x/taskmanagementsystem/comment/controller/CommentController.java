package site.ph0en1x.taskmanagementsystem.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.ph0en1x.taskmanagementsystem.auth.validation.OnUpdate;
import site.ph0en1x.taskmanagementsystem.comment.entity.Comment;
import site.ph0en1x.taskmanagementsystem.comment.entity.CommentMapper;
import site.ph0en1x.taskmanagementsystem.comment.entity.dto.CommentDto;
import site.ph0en1x.taskmanagementsystem.comment.service.CommentService;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "Comments controller", description = "Comment API")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper mapper;
    @PutMapping
    @Operation(summary = "Update comments")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#commentDto.getAuthor.getId)")
    public CommentDto update(@Validated(OnUpdate.class) @RequestBody CommentDto commentDto) {
        Comment comment = mapper.toEntity(commentDto);
        Comment out = commentService.update(comment);
        return mapper.toDto(out);
    }

}
