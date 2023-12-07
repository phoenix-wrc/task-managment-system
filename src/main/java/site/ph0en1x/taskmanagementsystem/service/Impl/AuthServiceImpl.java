package site.ph0en1x.taskmanagementsystem.service.Impl;

import org.springframework.stereotype.Service;
import site.ph0en1x.taskmanagementsystem.model.dto.auth.JwtRequest;
import site.ph0en1x.taskmanagementsystem.model.dto.auth.JwtResponse;
import site.ph0en1x.taskmanagementsystem.service.AuthService;

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
