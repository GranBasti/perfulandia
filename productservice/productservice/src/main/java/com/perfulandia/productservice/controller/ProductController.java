package com.perfulandia.productservice.controller;

import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.model.User;
import com.perfulandia.productservice.repository.ProductRepository;
import com.perfulandia.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

//Nuevas importaciones DTO conexión al MS usuario
import org.springframework.web.client.RestTemplate;

//Para hacer peticiones HTTP a otros microservicios



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

    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    @PostMapping
    public Producto save(@RequestBody Producto producto) {
        return service.save(producto);
    }


    @GetMapping("/{id}")
    public Producto buscar(@PathVariable long id) {
        return service.buscar(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }


    //Nuevo método
    @GetMapping("/usuario/{id}")
        public User obtenerUsuario(@PathVariable long id){
            return restTemplate.getForObject("http://localhost:8081/api/v1/usuarios/"+id, User.class);
        }

}
