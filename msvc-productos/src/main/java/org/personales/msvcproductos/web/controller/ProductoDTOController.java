package org.personales.msvcproductos.web.controller;

import lombok.AllArgsConstructor;
import org.personales.msvcproductos.domain.dtos.ProductoDTO;
import org.personales.msvcproductos.domain.service.ProductoDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductoDTOController {

    @Autowired
    private ProductoDTOService productoDTOService;

    @GetMapping("/listar")
    public ResponseEntity<List<ProductoDTO>> productos(){
        return new ResponseEntity<>(productoDTOService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/listar/{productoId}")
    public ResponseEntity<?> obtenerProducto(@PathVariable Long productoId){
        return productoDTOService.getProducto(productoId)
                .map(producto -> new ResponseEntity<>(producto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}