package com.perfulandia.shoppingcartservice.service;

import com.perfulandia.shoppingcartservice.model.CarritoCompra;
import com.perfulandia.shoppingcartservice.model.Producto;
import com.perfulandia.shoppingcartservice.repository.CartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;



@Service
public class CartService {

    private final CartRepository cartRepository;
    private final RestTemplate restTemplate;
    //private final RestTemplate restTemplate;

    //Constructor
    public CartService(CartRepository cartRepository, RestTemplate restTemplate) {
        this.cartRepository = cartRepository;
        this.restTemplate = restTemplate;
    }


    //Listar
    public List<CarritoCompra> listar() {
        return cartRepository.findAll();
    }


    //guardar
    public CarritoCompra save(CarritoCompra carritoCompra) {
      Producto producto = restTemplate.getForObject(
             "http://localhost:8082/api/v1/productos/" + carritoCompra.getId(), Producto.class);
     if(producto == null){
        throw new RuntimeException("Producto no encontrado");
     }
        return cartRepository.save(carritoCompra);
    }

    //Buscar x ID
    public CarritoCompra buscar(long id) {
        return cartRepository.findById(id).orElse(null);
    }

    //Eliminar x ID
    public void delete(long id) {
        cartRepository.deleteById(id);
    }


    public CarritoCompra agregarProducto(long carritoId, Producto producto, int cantidad) {
       CarritoCompra carrito = cartRepository.findById(carritoId).orElse(null);

        if (carrito == null) {
           carrito = new CarritoCompra();
       }

        carrito.setCantidad(carrito.getCantidad() + cantidad);
        carrito.setTotal(carrito.getTotal() + (producto.getPrecio() * cantidad));

        return cartRepository.save(carrito);
   }
    public Producto obtenerProducto(long id) {
        return restTemplate.getForObject("http://localhost:8082/api/v1/productos/" + id, Producto.class);
    }



}

