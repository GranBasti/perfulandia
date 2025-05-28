package com.perfulandia.shoppingcartservice.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Producto {
    private Long id;
    private String nombre;
    private Double precio;
    private int stock;
}
