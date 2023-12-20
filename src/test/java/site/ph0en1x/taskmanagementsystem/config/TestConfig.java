package site.ph0en1x.taskmanagementsystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import site.ph0en1x.taskmanagementsystem.auth.service.AuthService;
import site.ph0en1x.taskmanagementsystem.auth.service.impl.AuthServiceImpl;
import site.ph0en1x.taskmanagementsystem.auth.service.props.JwtProperties;
import site.ph0en1x.taskmanagementsystem.comment.repo.CommentRepository;
import site.ph0en1x.taskmanagementsystem.comment.service.CommentService;
import site.ph0en1x.taskmanagementsystem.comment.service.impl.CommentServiceImpl;
import site.ph0en1x.taskmanagementsystem.security.JwtTokenProvider;
import site.ph0en1x.taskmanagementsystem.security.JwtUserDetailsService;
import site.ph0en1x.taskmanagementsystem.task.TaskRepository;
import site.ph0en1x.taskmanagementsystem.task.service.TaskService;
import site.ph0en1x.taskmanagementsystem.task.service.impl.TaskServiceImpl;
import site.ph0en1x.taskmanagementsystem.user.AppUserRepository;
import site.ph0en1x.taskmanagementsystem.user.service.UserService;
import site.ph0en1x.taskmanagementsystem.user.service.UserServiceImpl;

@TestConfiguration
@RequiredArgsConstructor
public class TestConfig {

    @Autowired
    private final AppUserRepository appUserRepository;

    @Autowired
    private final TaskRepository taskRepository;

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Bean
    @Primary
    public BCryptPasswordEncoder testPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtProperties jwtProperties() {
        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setSecret("0J3QtdC80L3QvtCz0L7QodC+0LvQuNCU0LvRj9CS0LDRiNC10LPQvtCn0LDRj9CY0LvQuNCa0L7RhNC1");
        return jwtProperties;
    }

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        return new JwtUserDetailsService(userService());
    }

    @Bean
    public JwtTokenProvider tokenProvider() {
        return new JwtTokenProvider(jwtProperties(), userDetailsService(), userService());
    }

    @Bean
    @Primary
    public UserService userService() {
        return new UserServiceImpl(appUserRepository, testPasswordEncoder());
    }

    @Bean
    @Primary
    public TaskService taskService() {
        return new TaskServiceImpl(taskRepository, userService());
    }

    @Bean
    @Primary
    public CommentService commentService() {
        return new CommentServiceImpl(commentRepository);
    }

    @Bean
    @Primary
    public AuthService authService() {
        return new AuthServiceImpl(authenticationManager, userService(), tokenProvider());
    }

//    @Bean
//    @Primary
//    public CommentRepository commentRepo() {
//
//    }
//
//    @Bean
//    @Primary
//    public AppUserRepository appUserRepository() {
//
//    }
//
//    @Bean
//    @Primary
//    public TaskRepository taskRepository() {
//
//    }
}
