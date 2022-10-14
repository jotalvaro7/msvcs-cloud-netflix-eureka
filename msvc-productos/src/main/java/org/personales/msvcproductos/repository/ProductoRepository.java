package org.personales.msvcproductos.repository;

import org.personales.msvcproductos.models.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<Producto, Long> {


}
