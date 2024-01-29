package andrehsvictor.parrot.application.usecase.auth;

import andrehsvictor.parrot.application.dto.TokenResponse;

public interface RefreshUseCase {
    TokenResponse refresh(String refreshToken);
}
