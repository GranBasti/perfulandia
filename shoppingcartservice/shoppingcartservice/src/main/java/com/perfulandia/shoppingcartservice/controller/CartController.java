package com.perfulandia.shoppingcartservice.controller;

import com.perfulandia.shoppingcartservice.model.CarritoCompra;
import com.perfulandia.shoppingcartservice.model.Producto;
import com.perfulandia.shoppingcartservice.model.User;
import com.perfulandia.shoppingcartservice.repository.CartRepository;
import com.perfulandia.shoppingcartservice.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/carrito_de_compras")
public class CartController {

    private final CartService cartService;
    private final RestTemplate restTemplate;

    //constructor
    public CartController(CartService cartService, RestTemplate restTemplate) {
        this.cartService = cartService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<CarritoCompra> listar() {
        return cartService.listar();
    }

    @PostMapping
    public CarritoCompra save(@RequestBody CarritoCompra carritoCompra) {
        return cartService.save(carritoCompra);
    }

    @GetMapping("/{id}")
    public CarritoCompra buscar(@PathVariable long id) {
        return cartService.buscar(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        cartService.delete(id);
    }

    @GetMapping("/usuario/{id}")
    public User obtenerUsuario(@PathVariable long id) {
        return restTemplate.getForObject("http://localhost:8081/api/v1/usuarios/" + id, User.class);
    }

    @GetMapping("/producto/{id}")
    public Producto obtenerProductoDelCarrito(@PathVariable long id) {
      //  CarritoCompra carrito = cartService.buscar(id);
      //  if(carrito == null){
      //      throw new RuntimeException("Carrito no encontrado");
     //   }
        return restTemplate.getForObject("http://localhost:8082/api/v1/productos/" + id, Producto.class);
       //return cartService.obtenerProducto(carrito.getId());
    }

   @PostMapping("/agregar_producto/{id}")
   public CarritoCompra agregarProducto(@PathVariable long id, @RequestBody Producto producto, @RequestParam int cantidad) {
    return cartService.agregarProducto(id, producto, cantidad);
   }


}
