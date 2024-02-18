package site.ph0en1x.taskmanagementsystem.service;

import site.ph0en1x.taskmanagementsystem.web.dto.auth.JwtRequest;
import site.ph0en1x.taskmanagementsystem.web.dto.auth.JwtResponse;

public interface AuthService {
    JwtResponse login(JwtRequest loginRequest);
    JwtResponse refresh(String refreshToken);
}
