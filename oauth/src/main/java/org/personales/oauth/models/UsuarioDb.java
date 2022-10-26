package org.personales.oauth.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;


@Getter
@Setter
public class UsuarioDb {
    private Long id;
    private String username;
    private String password;
    private Boolean enabled;
    private String nombre;
    private String apellido;
    private String email;
    private Collection<Role> roles;
}
