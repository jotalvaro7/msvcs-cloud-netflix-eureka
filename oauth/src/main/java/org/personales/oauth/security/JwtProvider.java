package org.personales.oauth.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.personales.oauth.models.Role;
import org.personales.oauth.models.UsuarioDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@RefreshScope
@Component
public class JwtProvider {

    @Autowired
    private Environment env;

    public String createToken(UsuarioDb usuarioDb) {

        return Jwts.builder()
                .setClaims(setClaims(usuarioDb))
                .setIssuedAt(expeditionDate())
                .setExpiration(expirationDate())
                .signWith(getSecretKey())
                .compact();
    }

    private Map<String, Object> setClaims(UsuarioDb usuarioDb) {
        Map<String, Object> claims = Jwts.claims().setSubject(usuarioDb.getUsername());
        claims.put("nombre", usuarioDb.getNombre());
        claims.put("apellido", usuarioDb.getApellido());
        claims.put("email", usuarioDb.getEmail());
        claims.put("roles", getRolesWithOutId(usuarioDb));
        return claims;
    }

    private Date expeditionDate() {
        return new Date();
    }

    private Date expirationDate() {
        return new Date(expeditionDate().getTime() + 3600 * 1000);
    }

    public List<String> getRolesWithOutId(UsuarioDb usuarioDb) {
        return usuarioDb.getRoles().stream().map(Role::getNombre).collect(Collectors.toList());
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(env.getProperty("config.security.oauth.jwt.key").getBytes());
    }

}
