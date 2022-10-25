package org.personales.msvcusuarios.services;

import org.personales.msvcusuarios.models.entities.Usuario;
import org.personales.msvcusuarios.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> getUserById(Long id){
        return usuarioRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> getUserByUsername(String username){
        return usuarioRepository.findByUsername(username);
    }

    @Transactional
    public void deleteUserById(Long id){
        usuarioRepository.deleteById(id);
    }



}
