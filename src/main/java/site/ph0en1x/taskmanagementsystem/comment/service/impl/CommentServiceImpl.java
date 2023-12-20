package site.ph0en1x.taskmanagementsystem.comment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.ph0en1x.taskmanagementsystem.comment.repo.CommentRepository;
import site.ph0en1x.taskmanagementsystem.comment.entity.Comment;
import site.ph0en1x.taskmanagementsystem.comment.service.CommentService;
import site.ph0en1x.taskmanagementsystem.security.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Override
    public List<Comment> getAllCommentsFromTaskId(Long taskId) {
        return repository.findAllByTaskId(taskId);
    }

    @Override
    public Comment getById(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Comment not found"));
    }

    @Override
    @Transactional
    public Comment update(Comment comment) {
        return repository.save(comment);
    }

    @Override
    @Transactional
    public Comment create(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public boolean isCommentOwner(Long userId, Long commentId) {
        return repository.isCommentOwner(userId, commentId);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
