package org.personales.msvcitem.persistence;

import org.personales.msvcitem.domain.dto.ItemDTO;
import org.personales.msvcitem.domain.dto.ProductoDTO;
import org.personales.msvcitem.domain.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceRestTemplateImpl implements ItemService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<ItemDTO> getAll() {
        List<ProductoDTO> productoDTOS = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject("http://localhost:8001/productos", ProductoDTO[].class)));
        return productoDTOS.stream().map(productoDTO -> new ItemDTO(productoDTO, 1)).collect(Collectors.toList());
    }

    @Override
    public ItemDTO getItemById(Long id, Integer cantidad) {
        ProductoDTO productoDTO = restTemplate.getForObject("http://localhost:8001/productos/{id}", ProductoDTO.class, id.toString());
        return new ItemDTO(productoDTO, cantidad);
    }

    @Override
    public ProductoDTO save(ProductoDTO productoDTO) {
        return null;
    }

    @Override
    public ProductoDTO update(Long id, ProductoDTO productoDTO) {
        return null;
    }


    @Override
    public void deleteById(Long productoId) {

    }

}
