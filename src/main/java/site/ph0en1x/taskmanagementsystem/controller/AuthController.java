package site.ph0en1x.taskmanagementsystem.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.ph0en1x.taskmanagementsystem.model.dto.auth.JwtRequest;
import site.ph0en1x.taskmanagementsystem.model.dto.auth.JwtResponse;
import site.ph0en1x.taskmanagementsystem.model.dto.user.UserDto;
import site.ph0en1x.taskmanagementsystem.model.dto.validation.OnCreate;
import site.ph0en1x.taskmanagementsystem.model.entity.user.User;
import site.ph0en1x.taskmanagementsystem.model.mappers.UserMapper;
import site.ph0en1x.taskmanagementsystem.service.AuthService;
import site.ph0en1x.taskmanagementsystem.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "Auth controller", description = "Auth API")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest) {
        log.debug("Get login request with login:{} and password: {}"
                , loginRequest.getUsername(), loginRequest.getPassword());
        return authService.login(loginRequest);
    }

    @PostMapping("/registration")
    public UserDto registration(@Validated(OnCreate.class) @RequestBody UserDto dto) {
        log.debug("Get registration request with login:{} and password: {}"
                , dto.getUsername(), dto.getPassword());
        User user = userMapper.toEntity(dto);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        log.debug("receive refresh token {}", refreshToken);
        return authService.refresh(refreshToken);
    }

}
