package site.ph0en1x.taskmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.ph0en1x.taskmanagementsystem.exception.ResourceNotFoundException;
import site.ph0en1x.taskmanagementsystem.model.user.Role;
import site.ph0en1x.taskmanagementsystem.model.user.User;
import site.ph0en1x.taskmanagementsystem.repository.UserRepository;
import site.ph0en1x.taskmanagementsystem.service.UserService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository repo;

    private final PasswordEncoder passEncoder;
    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by ID"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return repo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by ID"));
    }

    @Override
    @Transactional
    public User update(User user) {
        user.setPassword(passEncoder.encode(user.getPassword()));
        repo.update(user);
        return user;
    }

    @Override
    @Transactional
    public User create(User user) {
        if (repo.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("User with this username already exists");
        }
        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            throw new IllegalStateException("Password and password confirmation do not match");
        }
        user.setPassword(passEncoder.encode(user.getPassword()));
        repo.create(user);
        Set<Role> roles = Set.of(Role.ROLE_USER);
        repo.insertUserRole(user.getId(), Role.ROLE_USER);
        user.setRoles(roles);
        return user;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repo.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTaskOwner(Long userId, Long taskId) {
        return repo.isTaskOwner(userId, taskId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTaskExecutor(Long userId, Long taskId) {
        return repo.isTaskExecutor(userId, taskId);
    }
}
