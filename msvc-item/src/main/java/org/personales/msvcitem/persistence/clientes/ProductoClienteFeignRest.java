package org.personales.msvcitem.persistence.clientes;

import org.personales.msvcitem.domain.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "servicio-productos", url = "localhost:8001/productos")
public interface ProductoClienteFeignRest {

    @GetMapping()
    List<ProductoDTO> getAll();

    @GetMapping("/{productoId}")
    ProductoDTO getProductoById(@PathVariable Long productoId);

}