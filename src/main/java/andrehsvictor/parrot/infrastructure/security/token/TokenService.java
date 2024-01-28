package andrehsvictor.parrot.infrastructure.security.token;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import andrehsvictor.parrot.application.dto.TokenResponse;
import andrehsvictor.parrot.infrastructure.exception.InvalidTokenException;
import andrehsvictor.parrot.infrastructure.persistence.user.UserEntity;
import andrehsvictor.parrot.infrastructure.security.auth.UserDetailsServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenService {
    @Value("${parrot.jwt.secret-key}")
    private String secretKey;

    private final String ACCESS_TOKEN_PREFIX = "Bearer ";

    private final String REFRESH_TOKEN_PREFIX = "Refresh ";

    private final String REFRESH_TOKEN_HEADER = "Refresh";

    private Algorithm algorithm;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostConstruct
    private void init() {
        algorithm = Algorithm.HMAC256(secretKey);
    }

    public TokenResponse generateToken(UserEntity user) {
        Long userId = user.getId();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = now.plusMinutes(15);
        String accessToken = getAccessToken(userId, now, expiresAt);
        String refreshToken = getRefreshToken(userId, now, expiresAt);

        return TokenResponse.builder().userId(userId).createdAt(now).expiresAt(expiresAt).accessToken(accessToken)
                .refreshToken(refreshToken).build();
    }

    private String getAccessToken(Long userId, LocalDateTime now, LocalDateTime expiresAt) {
        return JWT.create()
                .withClaim("user_id", userId)
                .withIssuedAt(Timestamp.valueOf(now))
                .withExpiresAt(Timestamp.valueOf(expiresAt))
                .withClaim("type", "access")
                .withIssuer("parrot")
                .sign(algorithm);
    }

    private String getRefreshToken(Long userId, LocalDateTime now, LocalDateTime expiresAt) {
        return JWT.create()
                .withClaim("user_id", userId)
                .withIssuedAt(Timestamp.valueOf(now))
                .withExpiresAt(Timestamp.valueOf(expiresAt.plusMinutes(15)))
                .withClaim("type", "refresh")
                .withIssuer("parrot")
                .sign(algorithm);
    }

    public String extractAccessToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith(ACCESS_TOKEN_PREFIX)) {
            return null;
        }
        return authorizationHeader.substring(ACCESS_TOKEN_PREFIX.length());
    }

    public String extractRefreshToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(REFRESH_TOKEN_HEADER);
        if (authorizationHeader == null || !authorizationHeader.startsWith(REFRESH_TOKEN_PREFIX)) {
            return null;
        }
        return authorizationHeader.substring(REFRESH_TOKEN_PREFIX.length());
    }

    private DecodedJWT decodeToken(String token) {
        return JWT.require(algorithm).build().verify(token);
    }

    public Authentication getAuthentication(String token) {
        DecodedJWT decodedToken = decodeToken(token);
        Long userId = decodedToken.getClaim("user_id").asLong();
        UserDetails userDetails = userDetailsService.loadUserById(userId);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            DecodedJWT decodedJWT = decodeToken(token);
            return !decodedJWT.getExpiresAt().before(Timestamp.valueOf(LocalDateTime.now()));
        } catch(Exception e) {
            throw new InvalidTokenException("Invalid token");
        }
    }

}
