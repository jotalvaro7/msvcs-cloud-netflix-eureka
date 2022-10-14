package org.personales.msvcproductos.persistance.repository;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.personales.msvcproductos.domain.dtos.ProductoDTO;
import org.personales.msvcproductos.domain.repository.ProductoDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class ProductoRepositoryImp implements ProductoDTORepository {

    @Autowired
    private ProductoJpaRepository productoJPARepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> getAll() {
        return productoJPARepository.findAll()
                .stream()
                .map(producto -> mapper.map(producto, ProductoDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductoDTO> getProducto(Long id) {
        return productoJPARepository.findById(id)
                .map(producto -> mapper.map(producto, ProductoDTO.class));
    }

}
