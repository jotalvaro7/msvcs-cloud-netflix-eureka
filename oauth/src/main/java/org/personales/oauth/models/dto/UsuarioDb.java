package org.personales.oauth.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private List<Role> roles;
}
