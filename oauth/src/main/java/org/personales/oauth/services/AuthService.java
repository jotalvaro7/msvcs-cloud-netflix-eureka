package org.personales.oauth.services;

import org.personales.oauth.clients.UsuarioFeignClient;
import org.personales.oauth.config.JwtProvider;
import org.personales.oauth.models.dto.Token;
import org.personales.oauth.models.dto.Usuario;
import org.personales.oauth.models.dto.UsuarioDb;
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

    public Token login(Usuario usuario) {
        UsuarioDb usuarioDb = client.findByUsername(usuario.getUsername());

        if (!passwordEncoder.matches(usuario.getPassword(), usuarioDb.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else {
            return new Token(jwtProvider.createToken(usuarioDb));
        }

    }

}
