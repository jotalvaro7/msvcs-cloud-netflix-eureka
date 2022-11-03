package org.personales.oauth.services;

import lombok.extern.slf4j.Slf4j;
import org.personales.oauth.clients.UsuarioFeignClient;
import org.personales.oauth.models.AuthCredentials;
import org.personales.oauth.security.JwtProvider;
import org.personales.oauth.models.Token;
import org.personales.oauth.models.UsuarioDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private UsuarioFeignClient client;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private Tracer tracer;

    public Token login(AuthCredentials authCredentials) {

        try {
            UsuarioDb usuarioDb = client.findByUsername(authCredentials.getUsername());

            if (passwordEncoder.matches(authCredentials.getPassword(), usuarioDb.getPassword())) {
                return new Token(jwtProvider.createToken(usuarioDb));
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            String error = "Error en el login, usuario o contraseña incorrectos";
            log.error(error);
            tracer.currentSpan().tag("error.mensaje", error + ": " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }

}
