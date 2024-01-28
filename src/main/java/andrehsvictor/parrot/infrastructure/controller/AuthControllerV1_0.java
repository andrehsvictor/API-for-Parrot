package andrehsvictor.parrot.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import andrehsvictor.parrot.application.dto.SigninRequest;
import andrehsvictor.parrot.application.dto.SignupRequest;
import andrehsvictor.parrot.application.usecase.AuthService;

@RestController
@RequestMapping("/api/1.0/auth")
public class AuthControllerV1_0 {
    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok(authService.signin(signinRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(signupRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader(name = "Refresh-Token", required = true) String refreshToken) {
        return ResponseEntity.ok(authService.refresh(refreshToken));
    }
}
