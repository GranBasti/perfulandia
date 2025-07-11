package com.perfulandia.usuarioservice.controller;

import com.perfulandia.usuarioservice.modelo.User;
import com.perfulandia.usuarioservice.repository.UserRepository;
import com.perfulandia.usuarioservice.service.UserService;
import com.perfulandia.usuarioservice.modelo.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name="User controlador", description = "Operaciones del CRUD para los EndPoints de User")
@RestController
@RequestMapping("/api/v1/usuarios")

public class UserController {

    private final UserService service;
    //Constructor para poder consumir la interfaz
    public UserController(UserService service) {
        this.service = service;
    }

    //GET//Operation para especificar un endpoint como tal
    @Operation(summary = "Obtiene todos los Users", description = "Devuelve una lista de todos los " +
            "users registrados en la base de Datos")
    @ApiResponse(responseCode = "200", description = "Consulta a los usuarios existosa")
    @GetMapping
    public List<User> listar(){
        return service.listar();
    }

    //POST
    @Operation(summary = "Crear un nuevo usuario", description = "Devuelve un JSON con el usuario agregado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en la validación")
    }
    )
    @PostMapping
    public User save(@RequestBody User user){
        return service.save(user);
    }

    //GET
    @Operation(summary = "Buscar un usuario", description = "Devuelve el usuario por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario buscado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se pudo buscar al usuario")

    })
    @GetMapping("/{id}")
    public User buscar(@PathVariable long id){
        return service.buscar(id);
    }

    //DELETE
    @Operation(summary = "Eliminar un usuario", description = "Devuelve una lista vacía")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "El ID del usuario no existe")

    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        service.delete(id);
    }
}
