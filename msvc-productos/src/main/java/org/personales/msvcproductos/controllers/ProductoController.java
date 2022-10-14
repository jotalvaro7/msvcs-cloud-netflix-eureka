package org.personales.msvcproductos.controllers;

import lombok.AllArgsConstructor;
import org.personales.msvcproductos.models.entity.Producto;
import org.personales.msvcproductos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping()
    public List<Producto> listar(){
        return productoService.findAll();
    }

    @GetMapping("/{productoId}")
    public Producto detalle(@PathVariable Long productoId){
        return productoService.findById(productoId);
    }

}
