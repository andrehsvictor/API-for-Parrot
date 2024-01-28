package andrehsvictor.parrot.application.usecase;

import andrehsvictor.parrot.application.dto.SigninRequest;
import andrehsvictor.parrot.application.dto.SignupRequest;
import andrehsvictor.parrot.application.dto.TokenResponse;

public interface AuthService {
    TokenResponse signin(SigninRequest signinRequest);
    TokenResponse signup(SignupRequest signupRequest);
    TokenResponse refresh(String refreshToken);
}
