package andrehsvictor.parrot.infrastructure.security.auth;

import org.springframework.stereotype.Service;

import andrehsvictor.parrot.application.dto.SigninRequest;
import andrehsvictor.parrot.application.dto.SignupRequest;
import andrehsvictor.parrot.application.dto.TokenResponse;
import andrehsvictor.parrot.application.use_case.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public TokenResponse signin(SigninRequest signinRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signin'");
    }

    @Override
    public TokenResponse signup(SignupRequest signupRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signup'");
    }
    
}
