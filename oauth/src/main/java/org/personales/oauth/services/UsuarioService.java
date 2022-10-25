package org.personales.oauth.services;

import lombok.extern.slf4j.Slf4j;
import org.personales.oauth.clients.UsuarioFeignClient;
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
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioFeignClient client;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = client.findByUsername(username);

        if (usuario == null) {
            log.error("Error en el login: no existe el usuario {} en el sistema", username);
            throw new UsernameNotFoundException("Error en el Login, no existe el usuario '" + username + "' en el sistema");
        }

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> log.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());
        log.info("Usuario autenticado: " + username);

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true,
                true, true, authorities);
    }
}
