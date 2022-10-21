package org.personales.msvcproductos.domain.repository;

import org.personales.msvcproductos.domain.dtos.ProductoDTO;

import java.util.List;
import java.util.Optional;

public interface ProductoDTORepository {

    List<ProductoDTO> getAll();
    Optional<ProductoDTO> getProducto(Long productoId);
    ProductoDTO save(ProductoDTO productoDTO);
    void deleteById(Long productoId);

}
