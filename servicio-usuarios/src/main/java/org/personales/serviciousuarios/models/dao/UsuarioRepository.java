package org.personales.serviciousuarios.models.dao;

import org.personales.serviciousuarios.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "usuarios")
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

    @RestResource(path = "buscar-username")
    Usuario findByUsername(@Param("username") String username);

    //Consulta con Query nativa de SQL para buscar por username igual que el metodo anterior pero con Query nativa de SQL
    /*
    @Query("select u from Usuario u where u.username=?1")
    public Usuario obtenerPorUsername(String username);
    */

}

