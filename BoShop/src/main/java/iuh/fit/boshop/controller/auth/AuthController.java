package iuh.fit.boshop.controller.auth;

import iuh.fit.boshop.dto.request.LoginRequest;
import iuh.fit.boshop.dto.request.RefreshTokenRequest;
import iuh.fit.boshop.dto.request.RegisterRequest;
import iuh.fit.boshop.dto.response.ApiResponse;
import iuh.fit.boshop.dto.response.LoginResponse;
import iuh.fit.boshop.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody RegisterRequest request) {
        authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>("User registered successfully", null)
                );
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = authService.login(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new ApiResponse<>("User logged in successfully", loginResponse)
                );
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<LoginResponse>> refreshToken(@RequestBody RefreshTokenRequest request) {
        LoginResponse loginResponse = authService.refreshToken(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new ApiResponse<>("Refresh token successfully", loginResponse)
                );
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestBody RefreshTokenRequest request) {
        authService.logout(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new ApiResponse<>("User logged out successfully", null)
                );
    }
}
