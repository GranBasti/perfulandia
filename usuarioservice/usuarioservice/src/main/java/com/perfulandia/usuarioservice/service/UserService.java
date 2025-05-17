package com.perfulandia.usuarioservice.service;

import com.perfulandia.usuarioservice.modelo.User;
import com.perfulandia.usuarioservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;
    //Constructor para poder consultar la interfaz
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    //Métodos que trabajan con CRUD


    //Listar
    public List<User> listar() {
        return repo.findAll();
    }

    //Guardar
    public User save(User usuario){
        return repo.save(usuario);
    }

    //Buscar por ID
    public User buscar(long id){
        return repo.findById(id).orElse(null);
    }

    //Eliminar por ID
    public void delete(long id){
        repo.deleteById(id);
    }

}
