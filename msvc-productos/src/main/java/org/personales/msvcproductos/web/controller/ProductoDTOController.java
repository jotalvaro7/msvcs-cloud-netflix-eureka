package org.personales.msvcproductos.web.controller;

import lombok.AllArgsConstructor;
import org.personales.msvcproductos.domain.dtos.ProductoDTO;
import org.personales.msvcproductos.domain.service.ProductoDTOService;
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
public class ProductoDTOController {

    @Autowired
    private ProductoDTOService productoDTOService;

    @GetMapping()
    public List<ProductoDTO> productos(){
        return productoDTOService.getAll();
    }

    @GetMapping("/{productoId}")
    public Optional<ProductoDTO> obtenerProducto(@PathVariable Long productoId){
        return productoDTOService.getProducto(productoId);
    }

}