package andrehsvictor.parrot.application.dto;

import java.time.LocalDateTime;

public record TokenResponse(Long userId, String createdAt, String expiresAt, String accessToken,
        String refreshToken) {
    public static TokenResponseBuilder builder() {
        return new TokenResponseBuilder();
    }

    public static class TokenResponseBuilder {
        private Long userId;
        private String createdAt;
        private String expiresAt;
        private String accessToken;
        private String refreshToken;

        TokenResponseBuilder() {
        }

        public TokenResponseBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public TokenResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt.toString();
            return this;
        }

        public TokenResponseBuilder expiresAt(LocalDateTime expiresAt) {
            this.expiresAt = expiresAt.toString();
            return this;
        }

        public TokenResponseBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public TokenResponseBuilder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public TokenResponse build() {
            return new TokenResponse(userId, createdAt, expiresAt, accessToken, refreshToken);
        }

        public String toString() {
            return "TokenResponse.TokenResponseBuilder(userId=" + this.userId + ", createdAt=" + this.createdAt
                    + ", expiresAt=" + this.expiresAt + ", accessToken=" + this.accessToken + ", refreshToken="
                    + this.refreshToken + ")";
        }
    }

}