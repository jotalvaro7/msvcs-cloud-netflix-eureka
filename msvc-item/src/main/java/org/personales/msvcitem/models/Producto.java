package org.personales.msvcitem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Producto {

    private Long id;
    private String nombre;
    private Double precio;
    private Date createAt;

}
