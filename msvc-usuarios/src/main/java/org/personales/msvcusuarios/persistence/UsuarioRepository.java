package org.personales.msvcusuarios.persistence;

import org.personales.msvcusuarios.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Usando Query Methods de Spring Data JPA
    Optional<Usuario> findByUsername(String username);

    //Usando JPQL para hacer un query nativo de JPA
    @Query("select u from Usuario u where u.username = ?1")
    Optional<Usuario> obtenerPorUsername(String username);

}
