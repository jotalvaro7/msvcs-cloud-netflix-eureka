package org.personales.msvcproductos.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

        private Long id;
        private String nombre;
        private Double precio;
        private Date createAt;
        private Integer port;

}
