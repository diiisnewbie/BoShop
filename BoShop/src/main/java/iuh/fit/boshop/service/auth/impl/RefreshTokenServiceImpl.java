package iuh.fit.boshop.service.auth.impl;

import iuh.fit.boshop.model.RefreshToken;
import iuh.fit.boshop.model.User;
import iuh.fit.boshop.repository.RefreshTokenRepository;
import iuh.fit.boshop.service.auth.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-expiration}")
    private long refreshTokenDurationMs;


    @Override
    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiresAt(
                        Instant.now()
                                .plusMillis(refreshTokenDurationMs)
                )
                .revoked(false)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyExpiration(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Refresh token not found")
                );
        if(refreshToken.isRevoked()){
            throw new RuntimeException("Refresh token is revoked");
        }

        if(refreshToken.getExpiresAt().isBefore(Instant.now())){
            throw new RuntimeException("Refresh token is expired");
        }

        return refreshToken;
    }

    @Override
    public void revokeToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Refresh token not found")
                );
        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
    }


}
