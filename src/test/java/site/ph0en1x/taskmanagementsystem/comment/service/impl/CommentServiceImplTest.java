package site.ph0en1x.taskmanagementsystem.comment.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import site.ph0en1x.taskmanagementsystem.comment.entity.Comment;
import site.ph0en1x.taskmanagementsystem.comment.repo.CommentRepository;
import site.ph0en1x.taskmanagementsystem.config.TestConfig;
import site.ph0en1x.taskmanagementsystem.task.entity.Task;
import site.ph0en1x.taskmanagementsystem.user.entity.User;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @MockBean
    private CommentRepository repository;

    private final CommentServiceImpl service;
    private long currentId = 0;

    CommentServiceImplTest(@Autowired CommentServiceImpl service) {
        this.service = service;
    }

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