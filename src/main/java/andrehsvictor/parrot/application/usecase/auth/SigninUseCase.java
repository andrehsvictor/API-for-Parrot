package andrehsvictor.parrot.application.usecase.auth;

import andrehsvictor.parrot.application.dto.SigninRequest;
import andrehsvictor.parrot.application.dto.TokenResponse;

public interface SigninUseCase {
    TokenResponse signin(SigninRequest signinRequest);
}
