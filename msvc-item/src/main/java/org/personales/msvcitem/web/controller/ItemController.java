package org.personales.msvcitem.web.controller;


import org.personales.msvcitem.domain.dto.ItemDTO;
import org.personales.msvcitem.domain.dto.ProductoDTO;
import org.personales.msvcitem.domain.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private CircuitBreakerFactory cbFactory;

    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;

    @GetMapping("/listar")
    public List<ItemDTO> getAllItems(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestHeader(name = "token-request", required = false) String token){
        System.out.println(nombre);
        System.out.println(token);
        return itemService.getAll();
    }

    @GetMapping("/listar/{id}/cantidad/{cantidad}")
    public ItemDTO getItemById(@PathVariable Long id, @PathVariable Integer cantidad){
        return cbFactory.create("items")
                .run(() -> itemService.getItemById(id, cantidad), throwable -> metodoAlternativo(id, cantidad));
    }

    public ItemDTO metodoAlternativo(Long id, Integer cantidad){
        ItemDTO itemDTO = new ItemDTO();
        ProductoDTO producto = new ProductoDTO();
        itemDTO.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara Sony");
        producto.setPrecio(500.00);
        itemDTO.setProductoDTO(producto);
        return itemDTO;
    }

}
