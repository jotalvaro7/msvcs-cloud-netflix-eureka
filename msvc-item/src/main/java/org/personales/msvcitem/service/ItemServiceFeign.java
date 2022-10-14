package org.personales.msvcitem.service;

import org.personales.msvcitem.clientes.ProductoClienteRest;
import org.personales.msvcitem.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
public class ItemServiceFeign implements ItemService {

    @Autowired
    private ProductoClienteRest clientFeign;

    @Override
    public List<Item> findAll() {
        return clientFeign.getAll().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(clientFeign.getProductoById(id), cantidad);
    }
}

