package site.ph0en1x.taskmanagementsystem.service;

import site.ph0en1x.taskmanagementsystem.model.dto.auth.JwtRequest;
import site.ph0en1x.taskmanagementsystem.model.dto.auth.JwtResponse;

public interface AuthService {
    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
