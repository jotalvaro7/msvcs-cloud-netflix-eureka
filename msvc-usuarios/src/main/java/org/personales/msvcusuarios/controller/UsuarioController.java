package org.personales.msvcusuarios.controller;

import org.personales.msvcusuarios.models.entities.Usuario;
import org.personales.msvcusuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> usuarios(){
        return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/listar/{UsuarioId}")
    public ResponseEntity<?> usuario(@PathVariable Long UsuarioId){
        return usuarioService.getUserById(UsuarioId)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/listar/username/{username}")
    public ResponseEntity<?> usuario(@PathVariable String username){
        return usuarioService.getUserByUsername(username)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/editar/{UsuarioId}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable Long UsuarioId, @RequestBody Usuario usuario){
        return usuarioService.getUserById(UsuarioId)
                .map(usuarioDb -> {
                    usuarioDb.setUsername(usuario.getUsername());
                    usuarioDb.setPassword(usuario.getPassword());
                    usuarioDb.setNombre(usuario.getNombre());
                    usuarioDb.setApellido(usuario.getApellido());
                    usuarioDb.setEmail(usuario.getEmail());
                    usuarioDb.setRoles(usuario.getRoles());
                    return new ResponseEntity<>(usuarioService.save(usuarioDb), HttpStatus.OK);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/eliminar/{UsuarioId}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long UsuarioId){
        if (usuarioService.getUserById(UsuarioId).isEmpty()) {
            return new ResponseEntity<>("Usuario no encontrado con Id: " + UsuarioId , HttpStatus.NOT_FOUND);
        }
        usuarioService.deleteUserById(UsuarioId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
