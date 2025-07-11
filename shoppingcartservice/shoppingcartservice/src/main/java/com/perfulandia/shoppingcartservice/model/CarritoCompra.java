package com.perfulandia.shoppingcartservice.model;

import jakarta.persistence.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Calendar;
import java.util.List;

@Schema (description = "Entidad o Clase que representa un Carrito de compra")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CarritoCompra {
    @Schema (description = "Id o campo único para el Carrito de compra", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Schema (description = "Cantidad de productos que contiene el carrito de compras", example = "1")
    private int cantidad;
    @Schema(description = "Cantidad total de los productos que contiene el carrito", example = "1")
    private double total;

}
