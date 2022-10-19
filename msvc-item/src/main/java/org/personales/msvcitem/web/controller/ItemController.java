package org.personales.msvcitem.web.controller;


import org.personales.msvcitem.domain.dto.ItemDTO;
import org.personales.msvcitem.domain.dto.ProductoDTO;
import org.personales.msvcitem.domain.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private CircuitBreakerFactory cbFactory;

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

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
                .run(() -> itemService.getItemById(id, cantidad)/*,
                        throwable -> metodoAlternativo(id, cantidad, throwable)*/);
    }

    public ItemDTO metodoAlternativo(Long id, Integer cantidad, Throwable throwable){
        logger.info(throwable.getMessage());
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
