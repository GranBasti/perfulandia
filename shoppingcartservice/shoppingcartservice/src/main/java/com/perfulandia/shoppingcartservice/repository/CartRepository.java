package com.perfulandia.shoppingcartservice.repository;

import com.perfulandia.shoppingcartservice.model.CarritoCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository <CarritoCompra, Long> {
}
