package com.perfulandia.productservice.model;

import lombok.*;

//DTO DAta Transfer Objects = Objeto de transferencia de datos
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private long id;
    private String name,email, rol;


}
