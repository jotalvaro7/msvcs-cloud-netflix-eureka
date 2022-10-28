package org.personales.oauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.personales.oauth.models.AuthCredentials;
import org.personales.oauth.models.Token;
import org.personales.oauth.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody AuthCredentials authCredentials) {
        return ResponseEntity.ok(authService.login(authCredentials));
    }

}
