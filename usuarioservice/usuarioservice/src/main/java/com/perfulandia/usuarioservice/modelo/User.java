package com.perfulandia.usuarioservice.modelo;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //permite crear objetos de manera flexible = constructor flexible


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String rol; //Admin, Gerente, Usuario
}
