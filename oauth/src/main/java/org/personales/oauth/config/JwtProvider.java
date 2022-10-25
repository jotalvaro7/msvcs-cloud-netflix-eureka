package org.personales.oauth.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.personales.oauth.models.dto.UsuarioDb;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    public String createToken(UsuarioDb usuario){

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 3600000L);

        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .claim("roles", usuario.getRoles())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, "12345")
                .compact();
    }
}
