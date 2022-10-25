package org.personales.oauth.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Usuario {

        private Long id;
        private String username;
        private String password;
}
