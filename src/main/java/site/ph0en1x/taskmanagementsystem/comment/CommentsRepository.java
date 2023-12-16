package site.ph0en1x.taskmanagementsystem.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import site.ph0en1x.taskmanagementsystem.comment.entity.Comment;

import java.util.List;


public interface CommentsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTaskId(Long taskId);
}
