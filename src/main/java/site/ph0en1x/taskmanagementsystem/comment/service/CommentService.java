package site.ph0en1x.taskmanagementsystem.comment.service;

import site.ph0en1x.taskmanagementsystem.comment.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllCommentsFromTaskId(Long taskId);

    Comment getById(Long id);

    Comment update(Comment comment);

    Comment create(Comment comment);

    boolean isCommentOwner(Long userId, Long commentId);

    void delete(Long id);
}
