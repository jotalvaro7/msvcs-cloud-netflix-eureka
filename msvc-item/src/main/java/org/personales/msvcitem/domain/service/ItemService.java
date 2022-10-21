package org.personales.msvcitem.domain.service;

import org.personales.msvcitem.domain.dto.ItemDTO;
import org.personales.msvcitem.domain.dto.ProductoDTO;

import java.util.List;


public interface ItemService {

    List<ItemDTO> getAll();
    ItemDTO getItemById(Long id, Integer cantidad);

    ProductoDTO save(ProductoDTO productoDTO);
    ProductoDTO update(Long id,ProductoDTO productoDTO);
    void deleteById(Long productoId);

}
