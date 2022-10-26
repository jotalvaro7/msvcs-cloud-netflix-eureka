package org.personales.oauth.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.personales.oauth.models.UsuarioDb;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Map;


@Component
public class JwtProvider {

    private static final String SECRET_KEY = "secret";

    public String createToken(UsuarioDb usuarioDb){

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 3600000L);

        Map<String, Object> claims = Jwts.claims().setSubject(usuarioDb.getUsername());
        claims.put("nombre", usuarioDb.getNombre());
        claims.put("apellido", usuarioDb.getApellido());
        claims.put("email", usuarioDb.getEmail());
        claims.put("authorities", usuarioDb.getRoles());


        return Jwts.builder()
                .setSubject(usuarioDb.getUsername())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    public void validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    public String getUsernameFromToken(String token){
       try{
           return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
       }catch (Exception e){
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token invalido");
       }
    }

}
