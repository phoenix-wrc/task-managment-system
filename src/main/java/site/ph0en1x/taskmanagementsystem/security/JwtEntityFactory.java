package site.ph0en1x.taskmanagementsystem.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import site.ph0en1x.taskmanagementsystem.model.entity.user.Role;
import site.ph0en1x.taskmanagementsystem.model.entity.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtEntityFactory {
    public static JwtEntity create(User user) {
        return new JwtEntity(
                user.getId(),
                user.getUsername(),
                user.getName() + " " + user.getSecondName() + " " + user.getLastName(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
