package andrehsvictor.parrot.infrastructure.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import andrehsvictor.parrot.application.dto.SigninRequest;
import andrehsvictor.parrot.application.dto.SignupRequest;
import andrehsvictor.parrot.application.dto.TokenResponse;
import andrehsvictor.parrot.application.usecase.AuthService;
import andrehsvictor.parrot.infrastructure.persistence.user.UserEntity;
import andrehsvictor.parrot.infrastructure.security.token.TokenService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public TokenResponse signin(SigninRequest signinRequest) {
        Authentication token = new UsernamePasswordAuthenticationToken(signinRequest.email(), signinRequest.password());
        Authentication authentication = authenticationManager.authenticate(token);
        return tokenService.generateToken((UserEntity) authentication.getPrincipal());
    }

    @Override
    public TokenResponse signup(SignupRequest signupRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signup'");
    }

    @Override
    public TokenResponse refresh(String refreshToken) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refresh'");
    }

}
