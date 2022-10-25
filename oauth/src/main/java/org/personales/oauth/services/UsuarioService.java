package org.personales.oauth.services;

import lombok.extern.slf4j.Slf4j;
import org.personales.oauth.clients.UsuarioFeignClient;
import org.personales.oauth.models.dto.Token;
import org.personales.oauth.models.dto.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UsuarioService {

    @Autowired
    private UsuarioFeignClient client;

    public Token login(Usuario usuario) {
        Usuario usuarioDB = client.findByUsername(usuario.getUsername());
        if (usuarioDB == null) {
            log.error("Error en el login: no existe el usuario '" + usuario.getUsername() + "' en el sistema");
            throw new UsernameNotFoundException("Error en el login: no existe el usuario '" + usuario.getUsername() + "' en el sistema");
        }
    return null;

    }
}
