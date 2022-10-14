package org.personales.msvcproductos.persistance.repository;

import org.personales.msvcproductos.persistance.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoJpaRepository extends JpaRepository<Producto, Long> {


}
