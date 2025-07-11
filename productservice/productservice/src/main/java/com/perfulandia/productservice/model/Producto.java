package com.perfulandia.productservice.model;

import jakarta.persistence.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema (description = "Entidad o Clase que representa un Producto")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //permite crear objetos de manera flexible = constructor flexible

public class Producto {
    @Schema (description = "Id o campo único para el Producto", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema (description = "Nombre que representa al Producto", example = "King")
    private String nombre;
    @Schema (description = "Precio de un Producto", example = "180000.0")
    private Double precio;
    @Schema (description = "Stock de un Producto", example = "10")
    private int stock;
}
