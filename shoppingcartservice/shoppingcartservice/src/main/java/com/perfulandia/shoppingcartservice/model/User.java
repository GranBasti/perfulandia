package com.perfulandia.shoppingcartservice.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private long id;
    private String name, email, rol;
}
