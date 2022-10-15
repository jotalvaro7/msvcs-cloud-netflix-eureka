package org.personales.msvcitem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private ProductoDTO productoDTO;
    private Integer cantidad;

    public Double getTotal() {
        return productoDTO.getPrecio() * cantidad.doubleValue();
    }

}
