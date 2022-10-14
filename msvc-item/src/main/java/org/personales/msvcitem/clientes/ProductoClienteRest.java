package org.personales.msvcitem.clientes;

import org.personales.msvcitem.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "servicio-productos", url = "localhost:8001/productos")
public interface ProductoClienteRest {

    @GetMapping()
    List<Producto> getAll();

    @GetMapping("/{productoId}")
    Producto getProductoById(@PathVariable Long productoId);

}
