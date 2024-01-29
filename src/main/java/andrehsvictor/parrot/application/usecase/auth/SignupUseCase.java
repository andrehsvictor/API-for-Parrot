package andrehsvictor.parrot.application.usecase.auth;

import andrehsvictor.parrot.application.dto.SignupRequest;
import andrehsvictor.parrot.application.dto.TokenResponse;

public interface SignupUseCase {
    TokenResponse signup(SignupRequest signupRequest);
}
