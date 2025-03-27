package com.arck.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private int code;
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;
}
