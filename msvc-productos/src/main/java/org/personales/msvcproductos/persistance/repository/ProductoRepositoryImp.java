package org.personales.msvcproductos.persistance.repository;

import org.modelmapper.ModelMapper;
import org.personales.msvcproductos.domain.dtos.ProductoDTO;
import org.personales.msvcproductos.domain.repository.ProductoDTORepository;
import org.personales.msvcproductos.persistance.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductoRepositoryImp implements ProductoDTORepository {

    @Autowired
    private ProductoJpaRepository productoJPARepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Environment env;

    @Value("${server.port}")
    private Integer port;


    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> getAll() {
        return productoJPARepository.findAll()
                .stream()
                .map(producto -> {
                    ProductoDTO productoDTO = mapper.map(producto, ProductoDTO.class);
                    productoDTO.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))));
                    //productoDTO.setPort(port);
                    return productoDTO;
                }).collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductoDTO> getProducto(Long id) {
        return productoJPARepository.findById(id)
                .map(producto -> {
                    ProductoDTO productoDTO = mapper.map(producto, ProductoDTO.class);
                    productoDTO.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))));
                    //productoDTO.setPort(port);
                    return productoDTO;
                });

    }

    @Override
    @Transactional
    public ProductoDTO save(ProductoDTO productoDTO) {
        Producto producto = mapper.map(productoDTO, Producto.class);
        Producto productoFromDb = productoJPARepository.save(producto);
        productoDTO.setId(productoFromDb.getId());
        return productoDTO;
    }

    @Override
    @Transactional
    public void deleteById(Long productoId) {
        productoJPARepository.deleteById(productoId);
    }

}
