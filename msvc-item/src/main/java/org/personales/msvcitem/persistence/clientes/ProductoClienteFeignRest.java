package org.personales.msvcitem.persistence.clientes;

import org.personales.msvcitem.domain.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteFeignRest {

    @GetMapping("/listar")
    List<ProductoDTO> getAll();

    @GetMapping("/listar/{productoId}")
    ProductoDTO getProductoById(@PathVariable Long productoId);

    @PostMapping("/crear")
    ProductoDTO save(@RequestBody ProductoDTO productoDTO);

    @PutMapping("/editar/{productoId}")
    ProductoDTO update(@PathVariable Long productoId,  @RequestBody ProductoDTO productoDTO);

    @DeleteMapping("/eliminar/{productoId}")
    void deleteById(@PathVariable Long productoId);
}
