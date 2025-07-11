package com.perfulandia.usuarioservice.modelo;

import jakarta.persistence.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema (description = "Entidad o Clase que representa un User")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //permite crear objetos de manera flexible = constructor flexible


public class User {
    @Schema (description = "Id o campo único para el User", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema (description = "Nombre que representa al User", example = "BASTI")
    private String name;
    @Schema(description = "Dirección de correo electrónico del User",
            example = "b@duoc.cl")
    private String email;
    @Schema(description = "Representa al rol que cumple el User en Perfulandia", example = "GERENTE" )
    private String rol; //Admin, Gerente, Usuario
}
