package iuh.fit.boshop.service.auth;

import iuh.fit.boshop.model.RefreshToken;
import iuh.fit.boshop.model.User;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(User user);
    RefreshToken verifyExpiration(String token);
    void revokeToken(String token);
}
