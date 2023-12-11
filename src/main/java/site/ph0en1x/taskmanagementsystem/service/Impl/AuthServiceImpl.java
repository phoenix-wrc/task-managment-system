package site.ph0en1x.taskmanagementsystem.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import site.ph0en1x.taskmanagementsystem.model.dto.auth.JwtRequest;
import site.ph0en1x.taskmanagementsystem.model.dto.auth.JwtResponse;
import site.ph0en1x.taskmanagementsystem.model.entity.user.User;
import site.ph0en1x.taskmanagementsystem.security.JwtTokenProvider;
import site.ph0en1x.taskmanagementsystem.service.AuthService;
import site.ph0en1x.taskmanagementsystem.service.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        log.debug("jwtResponse create successfully");
        log.debug("Get JwtRequest loginRequest with login:{} and password: {}",
                loginRequest.getUsername(), loginRequest.getPassword());
        var token = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                        loginRequest.getPassword());
        log.debug("UsernamePasswordAuthenticationToken create; with login:{} and password: {}",
                token.getName(), token.getCredentials());
        authenticationManager.authenticate(token);
        log.debug("authenticationManager successfully");
        User user = userService.getByUserName(loginRequest.getUsername());
        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(tokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles()));
        jwtResponse.setRefreshToken(tokenProvider.createRefreshToken(user.getId(), user.getUsername()));
        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return tokenProvider.refreshUserTokens(refreshToken);
    }
}
