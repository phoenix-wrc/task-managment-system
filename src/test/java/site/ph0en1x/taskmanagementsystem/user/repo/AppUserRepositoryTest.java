package site.ph0en1x.taskmanagementsystem.user.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import site.ph0en1x.taskmanagementsystem.config.TestConfig;
import site.ph0en1x.taskmanagementsystem.task.TaskRepository;
import site.ph0en1x.taskmanagementsystem.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
class AppUserRepositoryTest {

    @MockBean
    private AppUserRepository repository;
    private final AppUserRepository appUserRepository;

    AppUserRepositoryTest(@Autowired AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void findUserById() {
    }

    @Test
    void isTaskOwner() {
    }

    @Test
    void isTaskExecutor() {
    }
}