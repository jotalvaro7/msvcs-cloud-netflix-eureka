package org.personales.msvcitem.web.controller;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.personales.msvcitem.domain.dto.ItemDTO;
import org.personales.msvcitem.domain.dto.ProductoDTO;
import org.personales.msvcitem.domain.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RefreshScope
@Slf4j
@RestController
public class ItemController {

    @Autowired
    private Environment env;

    @Value("${configuracion.texto}")
    private String texto;

    @Autowired
    private CircuitBreakerFactory cbFactory;


    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;

    @GetMapping("/listar")
    public List<ItemDTO> getAllItems(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestHeader(name = "token-request", required = false) String token){
        log.info("Texto: {}", nombre);
        log.info("token: {} ", token);
        return itemService.getAll();
    }

    @GetMapping("/listar/{id}/cantidad/{cantidad}")
    public ItemDTO getItemById(@PathVariable Long id, @PathVariable Integer cantidad){
        return cbFactory.create("items")
                .run(() -> itemService.getItemById(id, cantidad),
                        throwable -> metodoAlternativo(id, cantidad, throwable));
    }


    @CircuitBreaker(name= "items", fallbackMethod = "metodoAlternativo")
    @GetMapping("/listar2/{id}/cantidad/{cantidad}")
    public ItemDTO getItemById2(@PathVariable Long id, @PathVariable Integer cantidad){
        return itemService.getItemById(id, cantidad);
    }


    @CircuitBreaker(name= "items", fallbackMethod = "metodoAlternativo2")
    @TimeLimiter(name = "items")
    @GetMapping("/listar3/{id}/cantidad/{cantidad}")
    public CompletableFuture<ItemDTO> getItemById3(@PathVariable Long id, @PathVariable Integer cantidad){
        return CompletableFuture.supplyAsync(() -> itemService.getItemById(id, cantidad));
    }

    public ItemDTO metodoAlternativo(Long id, Integer cantidad, Throwable throwable){
        log.info(throwable.getMessage());
        ItemDTO itemDTO = new ItemDTO();
        ProductoDTO producto = new ProductoDTO();
        itemDTO.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara Sony");
        producto.setPrecio(500.00);
        itemDTO.setProductoDTO(producto);
        return itemDTO;
    }

    public CompletableFuture<ItemDTO> metodoAlternativo2(Long id, Integer cantidad, Throwable throwable){
        log.info(throwable.getMessage());
        ItemDTO itemDTO = new ItemDTO();
        ProductoDTO producto = new ProductoDTO();
        itemDTO.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara Sony");
        producto.setPrecio(500.00);
        itemDTO.setProductoDTO(producto);
        return CompletableFuture.supplyAsync(() -> itemDTO);
    }


    @GetMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){
        log.info(texto);
        //creamos un map para guardar la configuraci??n
        Map<String, String> json = new HashMap<>();
        json.put("texto", texto);
        json.put("puerto", puerto);

        if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")){
            json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
            json.put("autor.email", env.getProperty("configuracion.autor.email"));
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<ProductoDTO> crear(@RequestBody ProductoDTO productoDTO){
        return new ResponseEntity<>(itemService.save(productoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ProductoDTO> editar(@PathVariable Long id, @RequestBody ProductoDTO productoDTO){
        return new ResponseEntity<>(itemService.update(id, productoDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        itemService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
