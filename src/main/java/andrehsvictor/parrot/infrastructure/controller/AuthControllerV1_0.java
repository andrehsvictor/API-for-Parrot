package andrehsvictor.parrot.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import andrehsvictor.parrot.application.dto.SigninRequest;
import andrehsvictor.parrot.application.usecase.auth.SigninUseCase;

@RestController
@RequestMapping("/api/v1.0/auth")
public class AuthControllerV1_0 {

    @Autowired
    private SigninUseCase signinUseCase;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok(signinUseCase.signin(signinRequest));
    }
}
