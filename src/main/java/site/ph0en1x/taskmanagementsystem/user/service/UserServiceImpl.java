package site.ph0en1x.taskmanagementsystem.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.ph0en1x.taskmanagementsystem.user.entity.Role;
import site.ph0en1x.taskmanagementsystem.user.entity.User;
import site.ph0en1x.taskmanagementsystem.security.exception.ResourceNotFoundException;
import site.ph0en1x.taskmanagementsystem.user.AppUserRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return appUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUserName(String username) {
        return appUserRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public User update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public User create(User user) {
        if (appUserRepository.findByUsername(user.getUsername()).isPresent()) {
            log.debug("seen that user already exists");
            throw new IllegalStateException("User already exists.");
        }
        log.debug("User with username:{} does not exist", user.getUsername());
        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            throw new IllegalStateException("Password and password confirmation does not match");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = Set.of(Role.ROLE_USER);
        user.setRoles(roles);
        appUserRepository.save(user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTaskOwner(Long userId, Long taskId) {
        return appUserRepository.isTaskOwner(userId, taskId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTaskExecutor(Long userId, Long taskId) {
        return appUserRepository.isTaskExecutor(userId, taskId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        appUserRepository.deleteById(id);
    }
}
