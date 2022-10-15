package org.personales.msvcitem.domain.service;

import org.personales.msvcitem.domain.dto.ItemDTO;
import java.util.List;


public interface ItemService {

    List<ItemDTO> getAll();
    ItemDTO getItemById(Long id, Integer cantidad);

}
