package com.perfulandia.productservice.service;

import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    //Se declara como final porque no cambia el dato
    private final ProductRepository repo;

    //Constructor
    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }


    //Listar
    public List<Producto> listar() {
        return repo.findAll();
    }

    //guardar
    public Producto save(Producto producto) {
        return repo.save(producto);
    }

    //Buscar por ID
    public Producto buscar(long id){
        return repo.findById(id).orElse(null);
    }


    //Eliminar por ID
    public void delete(long id){
        repo.deleteById(id);
    }

}
