package iuh.fit.boshop.dto.response;

public record AuthResponse (
    String accessToken,
    String refreshToken
){}
