package org.personales.msvcitem.service;

import lombok.AllArgsConstructor;
import org.personales.msvcitem.models.Item;
import org.personales.msvcitem.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<Item> findAll() {
        List<Producto> productos = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject("http://localhost:8001/productos", Producto[].class)));
        return productos.stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Producto producto = restTemplate.getForObject("http://localhost:8001/productos/{id}", Producto.class, id.toString());
        return new Item(producto, cantidad);
    }

}
