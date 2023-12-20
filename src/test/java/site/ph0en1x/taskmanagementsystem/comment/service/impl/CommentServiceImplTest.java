package site.ph0en1x.taskmanagementsystem.comment.service.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import site.ph0en1x.taskmanagementsystem.comment.entity.Comment;
import site.ph0en1x.taskmanagementsystem.comment.service.CommentService;
import site.ph0en1x.taskmanagementsystem.task.entity.Task;
import site.ph0en1x.taskmanagementsystem.user.entity.User;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RequiredArgsConstructor
//@Sql("/schema-hibernate.sql")
class CommentServiceImplTest {
    private final CommentService service;
    private long currentId = 0;

    private Comment getNewComment() {
        var comment = new Comment();
        comment.setTitle("Comment " + (currentId));
        comment.setTitle("description of comment " + (currentId));
        var task = new Task();
        task.setId(99L);
        var user = new User();
        user.setId(99L);
        comment.setTask(task);
        return comment;
    }

    @Test
    void getAllCommentsFromTaskId() {
        var out = service.create(getNewComment());
        currentId = out.getId();
        out = service.create(getNewComment());
        currentId = out.getId();
        out = service.create(getNewComment());
        currentId = out.getId();
        List<Comment> commentList = service.getAllCommentsFromTaskId(99L);

        assertThat(commentList).isNotNull();
        assertThat(commentList.size()).isEqualTo(3);
    }

    @Test
    void getById() {

    }

    @Test
    void update() {
    }

    @Test
    void create() {
    }

    @Test
    void isCommentOwner() {
    }

    @Test
    void delete() {
    }
}