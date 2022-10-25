package org.personales.oauth.clients;

import org.personales.oauth.models.dto.UsuarioDb;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "servicio-usuarios")
public interface UsuarioFeignClient {

    @GetMapping("/listar/username/{username}")
    UsuarioDb findByUsername(@RequestParam String username);


}
