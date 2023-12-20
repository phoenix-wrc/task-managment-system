package site.ph0en1x.taskmanagementsystem.task.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import site.ph0en1x.taskmanagementsystem.config.TestConfig;
import site.ph0en1x.taskmanagementsystem.task.TaskRepository;
import site.ph0en1x.taskmanagementsystem.task.entity.Task;
import site.ph0en1x.taskmanagementsystem.user.AppUserRepository;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private AppUserRepository appUserRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    private final TaskServiceImpl taskService;

    TaskServiceImplTest(@Autowired TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @Test
    void getById() {
        Long id = 99L;
        Task task = new Task();
        task.setId(id);
        Mockito.when(taskRepository.findById(id))
                .thenReturn(Optional.of(task));
        Task testTask = taskService.getById(id);
        Mockito.verify(taskRepository).findById(id);
        Assertions.assertEquals(testTask, task);
    }


    @Test
    void getAllByUserId() {
    }

    @Test
    void update() {
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }
}