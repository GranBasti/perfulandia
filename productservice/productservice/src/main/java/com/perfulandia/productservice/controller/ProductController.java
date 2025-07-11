package com.perfulandia.productservice.controller;

import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.model.User;
import com.perfulandia.productservice.repository.ProductRepository;
import com.perfulandia.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

//Nuevas importaciones DTO conexión al MS usuario
import org.springframework.web.client.RestTemplate;

//Para hacer peticiones HTTP a otros microservicios


@Tag(name="Producto controlador", description = "Operaciones del CRUD para los EndPoints de Producto")
@RestController
@RequestMapping("/api/v1/productos")

public class ProductController {

    private final ProductService service;

    private final RestTemplate restTemplate;

    //constructor
    public ProductController(ProductService service, RestTemplate restTemplate) {
        this.service = service;
        this.restTemplate = restTemplate;
    }

    //GET//Operation para especificar un endpoint como tal
    @Operation(summary = "Obtiene todos los productos", description = "Devuelve una lista de todos los " +
            "productos registrados en la base de Datos")
    @ApiResponse(responseCode = "200", description = "Consulta a los productos existosa")
    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    //POST
    @Operation(summary = "Crear un nuevo producto", description = "Devuelve un JSON con el producto creado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en la validación")
    }
    )
    @PostMapping
    public Producto save(@RequestBody Producto producto) {
        return service.save(producto);
    }

    //GET
    @Operation(summary = "Buscar un producto", description = "Devuelve el producto por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto buscado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se pudo buscar el producto")

    })
    @GetMapping("/{id}")
    public Producto buscar(@PathVariable long id) {
        return service.buscar(id);
    }

    //DELETE
    @Operation(summary = "Eliminar un producto", description = "Devuelve una lista vacía")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "El ID del producto no existe")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

    //GET
    @Operation(summary = "Buscar un usuario", description = "Devuelve el usaurio por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario buscado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se pudo buscar el usuario")

    })
    //Nuevo método
    @GetMapping("/usuario/{id}")
        public User obtenerUsuario(@PathVariable long id){
            return restTemplate.getForObject("http://localhost:8081/api/v1/usuarios/"+id, User.class);
        }

}
