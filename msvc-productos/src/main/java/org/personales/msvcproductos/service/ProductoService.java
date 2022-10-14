package org.personales.msvcproductos.service;

import org.personales.msvcproductos.models.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> findAll();
    Producto findById(Long id);

}
