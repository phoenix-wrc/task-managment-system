package site.ph0en1x.taskmanagementsystem.model.dto.auth;

import lombok.Data;

@Data
public class JwtResponse {
    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;
}
