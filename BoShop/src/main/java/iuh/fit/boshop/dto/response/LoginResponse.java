package iuh.fit.boshop.dto.response;

public record LoginResponse (
    String accessToken,
    String refreshToken
){}
