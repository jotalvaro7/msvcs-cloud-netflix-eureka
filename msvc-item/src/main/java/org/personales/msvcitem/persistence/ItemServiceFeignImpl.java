package org.personales.msvcitem.persistence;

import org.personales.msvcitem.domain.dto.ItemDTO;
import org.personales.msvcitem.domain.service.ItemService;
import org.personales.msvcitem.persistence.clientes.ProductoClienteFeignRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
public class ItemServiceFeignImpl implements ItemService {

    @Autowired
    private ProductoClienteFeignRest clientFeign;

    @Override
    public List<ItemDTO> getAll() {
        return clientFeign.getAll().stream().map(p -> new ItemDTO(p, 1)).collect(Collectors.toList());
    }

    @Override
    public ItemDTO getItemById(Long id, Integer cantidad) {
        return new ItemDTO(clientFeign.getProductoById(id), cantidad);
    }
}

