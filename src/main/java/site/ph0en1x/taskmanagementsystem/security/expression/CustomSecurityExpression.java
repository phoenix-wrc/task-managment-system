package site.ph0en1x.taskmanagementsystem.security.expression;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import site.ph0en1x.taskmanagementsystem.user.entity.Role;
import site.ph0en1x.taskmanagementsystem.security.JwtEntity;
import site.ph0en1x.taskmanagementsystem.user.service.UserService;

@Service("customSecurityExpression")
@RequiredArgsConstructor
public class CustomSecurityExpression {

    private final UserService userService;

    public boolean canAccessUser(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Long userId = user.getId();
        
        
        return userId.equals(id) || hasAnyRole(authentication, Role.ROLE_ADMIN);
    }

    public boolean canAccessSetStatus(Long taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Long userId = user.getId();

        return userService.isTaskExecutor(userId, taskId);
    }

    public boolean canAccesTask(Long taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Long userId = user.getId();

        return userService.isTaskOwner(userId, taskId);
    }

    private boolean hasAnyRole(Authentication authentication, Role ... roles) {
        for(Role role: roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
            if (authentication.getAuthorities().contains(authority)) {
                return true;
            }
        }
        return false;
    }
}
