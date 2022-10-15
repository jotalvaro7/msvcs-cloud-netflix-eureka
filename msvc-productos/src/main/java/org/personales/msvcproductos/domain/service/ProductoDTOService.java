package org.personales.msvcproductos.domain.service;

import lombok.AllArgsConstructor;
import org.personales.msvcproductos.domain.dtos.ProductoDTO;
import org.personales.msvcproductos.domain.repository.ProductoDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductoDTOService {

    @Autowired
    private ProductoDTORepository productoDTORepository;

    public List<ProductoDTO> getAll(){
        return productoDTORepository.getAll();
    }

    public Optional<ProductoDTO> getProducto(Long productoId){
        return productoDTORepository.getProducto(productoId);
    }


}
