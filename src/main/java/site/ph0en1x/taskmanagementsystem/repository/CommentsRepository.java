package site.ph0en1x.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.ph0en1x.taskmanagementsystem.model.entity.comment.Comment;

import java.util.List;


public interface CommentsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTaskId(Long taskId);
}
