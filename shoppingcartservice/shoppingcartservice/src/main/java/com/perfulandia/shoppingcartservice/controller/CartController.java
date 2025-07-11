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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Tag(name="Carrito de compra controlador", description = "Operaciones del CRUD para los EndPoints del carrito de compra")
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

    //GET//Operation para especificar un endpoint como tal
    @Operation(summary = "Obtiene todos los Carrito de compras", description = "Devuelve una lista de todos los " +
            "carritos de compra registrados en la base de Datos")
    @ApiResponse(responseCode = "200", description = "Consulta a los carritos de compras existosa")
    @GetMapping
    public List<CarritoCompra> listar() {
        return cartService.listar();
    }

    //POST
    @Operation(summary = "Crear un nuevo carrito de compras", description = "Devuelve un JSON con el carrito de compras creado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "carrito de compras creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en la validación")
    }
    )
    @PostMapping
    public CarritoCompra save(@RequestBody CarritoCompra carritoCompra) {
        return cartService.save(carritoCompra);
    }

    //GET
    @Operation(summary = "Buscar un carrito de compras", description = "Devuelve el carrito de compras por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "carrito de compras buscado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se pudo buscar al carrito de compras")

    })
    @GetMapping("/{id}")
    public CarritoCompra buscar(@PathVariable long id) {
        return cartService.buscar(id);
    }

    @Operation(summary = "Eliminar un carrito de compras", description = "Devuelve una lista vacía")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "carrito de compras eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "El ID del carrito de compras no existe")

    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        cartService.delete(id);
    }

    //GET
    @Operation(summary = "Buscar un usuario", description = "Devuelve el usuario por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "usuario buscado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se pudo buscar al usuario")

    })
    @GetMapping("/usuario/{id}")
    public User obtenerUsuario(@PathVariable long id) {
        return restTemplate.getForObject("http://localhost:8081/api/v1/usuarios/" + id, User.class);
    }

    //GET
    @Operation(summary = "Buscar un producto", description = "Devuelve el producto por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "producto buscado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se pudo buscar el producto")

    })
    @GetMapping("/producto/{id}")
    public Producto obtenerProductoDelCarrito(@PathVariable long id) {
      //  CarritoCompra carrito = cartService.buscar(id);
      //  if(carrito == null){
      //      throw new RuntimeException("Carrito no encontrado");
     //   }
        return restTemplate.getForObject("http://localhost:8082/api/v1/productos/" + id, Producto.class);
       //return cartService.obtenerProducto(carrito.getId());
    }

    //POSR
    @Operation(summary = "agregar un producto en el carrito de compras", description = "Devuelve el producto agregado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "producto agregado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se pudo agregar el producto")

    })
   @PostMapping("/agregar_producto/{id}")
   public CarritoCompra agregarProducto(@PathVariable long id, @RequestBody Producto producto, @RequestParam int cantidad) {
    return cartService.agregarProducto(id, producto, cantidad);
   }


}
