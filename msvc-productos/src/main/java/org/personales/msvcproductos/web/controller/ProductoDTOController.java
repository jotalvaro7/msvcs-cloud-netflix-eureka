package org.personales.msvcproductos.web.controller;

import lombok.RequiredArgsConstructor;
import org.personales.msvcproductos.domain.dtos.ProductoDTO;
import org.personales.msvcproductos.domain.service.ProductoDTOService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@RestController
public class ProductoDTOController {

    private final ProductoDTOService productoDTOService;

    @GetMapping("/listar")
    public ResponseEntity<List<ProductoDTO>> productos(){
        return new ResponseEntity<>(productoDTOService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/listar/{productoId}")
    public ResponseEntity<?> obtenerProducto(@PathVariable Long productoId) throws InterruptedException {

        if(productoId.equals(10L)){
            throw new IllegalStateException("El producto no existe");
        }
        if(productoId.equals(7L)){
            TimeUnit.SECONDS.sleep(5);
        }

        return productoDTOService.getProducto(productoId)
                .map(producto -> new ResponseEntity<>(producto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/crear")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO){
        return new ResponseEntity<>(productoDTOService.save(productoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/editar/{productoId}")
    public ResponseEntity<ProductoDTO> editarProducto(@PathVariable Long productoId, @RequestBody ProductoDTO newProductoDTO){
        return productoDTOService.getProducto(productoId)
                .map(productoDb -> {
                    productoDb.setNombre(newProductoDTO.getNombre());
                    productoDb.setPrecio(newProductoDTO.getPrecio());
                    return new ResponseEntity<>(productoDTOService.save(productoDb), HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/eliminar/{productoId}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long productoId){
        if(productoDTOService.getProducto(productoId).isEmpty()){
            return new ResponseEntity<>("Producto con id " + productoId + " no encontrado",HttpStatus.NOT_FOUND);
        }
        productoDTOService.deleteById(productoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}