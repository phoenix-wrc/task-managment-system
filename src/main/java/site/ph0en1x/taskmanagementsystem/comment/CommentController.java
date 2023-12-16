package site.ph0en1x.taskmanagementsystem.comment;

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
import site.ph0en1x.taskmanagementsystem.comment.entity.dto.CommentDto;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "Comments controller", description = "Comment API")
public class CommentController {
    @PutMapping
    @Operation(summary = "Update user")
    @PreAuthorize("@customSecurityExpression.canAccesUser(#userDto.id)")
    public CommentDto update(@Validated(OnUpdate.class) @RequestBody CommentDto commentDto) {
        return null;
    }

}
