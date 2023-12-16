package site.ph0en1x.taskmanagementsystem.auth.service;

import site.ph0en1x.taskmanagementsystem.auth.jwt.JwtRequest;
import site.ph0en1x.taskmanagementsystem.auth.jwt.JwtResponse;

public interface AuthService {
    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
