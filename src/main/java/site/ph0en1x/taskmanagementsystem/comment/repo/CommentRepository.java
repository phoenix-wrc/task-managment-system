package site.ph0en1x.taskmanagementsystem.comment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.ph0en1x.taskmanagementsystem.comment.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTaskId(Long taskId);

    @Query(value = """
            SELECT exists(SELECT *
              FROM "task-management-system".comment
              WHERE id = :commentId
                AND author_id = :userId)
            """, nativeQuery = true)
    boolean isCommentOwner(Long userId, Long commentId);
}
