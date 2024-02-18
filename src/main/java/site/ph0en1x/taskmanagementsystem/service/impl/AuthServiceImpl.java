package site.ph0en1x.taskmanagementsystem.service.impl;

import org.springframework.stereotype.Service;
import site.ph0en1x.taskmanagementsystem.service.AuthService;
import site.ph0en1x.taskmanagementsystem.web.dto.auth.JwtRequest;
import site.ph0en1x.taskmanagementsystem.web.dto.auth.JwtResponse;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }
}
