package andrehsvictor.parrot.application.use_case;

import andrehsvictor.parrot.application.dto.SigninRequest;
import andrehsvictor.parrot.application.dto.SignupRequest;
import andrehsvictor.parrot.application.dto.TokenResponse;

public interface AuthService {
    TokenResponse signin(SigninRequest signinRequest);
    TokenResponse signup(SignupRequest signupRequest);
}
