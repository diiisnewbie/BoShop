package iuh.fit.boshop.service.auth;

import iuh.fit.boshop.dto.request.LoginRequest;
import iuh.fit.boshop.dto.request.RefreshTokenRequest;
import iuh.fit.boshop.dto.request.RegisterRequest;
import iuh.fit.boshop.dto.response.LoginResponse;

public interface AuthService {

    void register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse refreshToken(RefreshTokenRequest request);

    void logout(RefreshTokenRequest request);
}
