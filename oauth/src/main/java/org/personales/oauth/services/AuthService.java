package org.personales.oauth.services;

import org.personales.oauth.clients.UsuarioFeignClient;
import org.personales.oauth.models.AuthCredentials;
import org.personales.oauth.security.JwtProvider;
import org.personales.oauth.models.Token;
import org.personales.oauth.models.UsuarioDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    private UsuarioFeignClient client;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    public Token login(AuthCredentials authCredentials) {
        UsuarioDb usuarioDb = client.findByUsername(authCredentials.getUsername());

        if (!passwordEncoder.matches(authCredentials.getPassword(), usuarioDb.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else {
            return new Token(jwtProvider.createToken(usuarioDb));
        }
    }

}
