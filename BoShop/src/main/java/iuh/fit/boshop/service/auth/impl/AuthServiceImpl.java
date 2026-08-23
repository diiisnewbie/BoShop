package iuh.fit.boshop.service.auth.impl;

import iuh.fit.boshop.config.CustomeUserDetails;
import iuh.fit.boshop.config.jwt.JwtService;
import iuh.fit.boshop.dto.request.LoginRequest;
import iuh.fit.boshop.dto.request.RefreshTokenRequest;
import iuh.fit.boshop.dto.request.RegisterRequest;
import iuh.fit.boshop.dto.response.LoginResponse;
import iuh.fit.boshop.exceptions.EmailAlreadyExistsException;
import iuh.fit.boshop.model.RefreshToken;
import iuh.fit.boshop.model.User;
import iuh.fit.boshop.model.enums.Role;
import iuh.fit.boshop.model.enums.UserStatus;
import iuh.fit.boshop.repository.UserRepository;
import iuh.fit.boshop.service.auth.AuthService;
import iuh.fit.boshop.service.auth.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public void register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(
                    "Email already exists"
            );
        }

        User user = User.builder()
                .email(request.email())
                .password(
                        passwordEncoder.encode(request.password())
                )
                .role(Role.USER)
                .status(UserStatus.ACTIVE)
                .build();

        userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );

        CustomeUserDetails customeUserDetails = (CustomeUserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(customeUserDetails);
        RefreshToken refreshToken = refreshTokenService.
                createRefreshToken(
                        customeUserDetails.getUser()
                );

        return new LoginResponse(token, refreshToken.getToken());
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken token = refreshTokenService
                .verifyExpiration(
                        request.refreshToken()
                );

        User user = token.getUser();

        CustomeUserDetails customeUserDetails = new CustomeUserDetails(user);

        String jwtToken = jwtService.generateToken(customeUserDetails);


        return new LoginResponse(jwtToken, request.refreshToken());
    }

    @Override
    public void logout(RefreshTokenRequest request) {
        refreshTokenService.revokeToken(request.refreshToken());
    }
}
