package com.perfulandia.usuarioservice.controller;

import com.perfulandia.usuarioservice.modelo.User;
import com.perfulandia.usuarioservice.repository.UserRepository;
import com.perfulandia.usuarioservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/usuarios")

public class UserController {

    private final UserService service;
    //Constructor para poder consumir la interfaz
    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping
    public List<User> listar(){
        return service.listar();
    }

    @PostMapping
    public User save(@RequestBody User user){
        return service.save(user);
    }


    @GetMapping("/{id}")
    public User buscar(@PathVariable long id){
        return service.buscar(id);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        service.delete(id);
    }
}
