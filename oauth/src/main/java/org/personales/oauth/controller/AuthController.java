package org.personales.oauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.personales.oauth.models.dto.Token;
import org.personales.oauth.models.dto.Usuario;
import org.personales.oauth.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(authService.login(usuario));
    }

}
