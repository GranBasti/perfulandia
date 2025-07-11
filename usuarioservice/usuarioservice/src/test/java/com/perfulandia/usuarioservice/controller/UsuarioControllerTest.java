package com.perfulandia.usuarioservice.controller;

import com.perfulandia.usuarioservice.modelo.User;

import com.perfulandia.usuarioservice.service.UserService;


//1 Importaciones de JUnit 5

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;



//2 Anotación para probar solo el controlador (no el contexto completo de Spring Boot)

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;



//3 Anotación para simular un bean dentro del ApplicationContext de Spring

import org.springframework.test.context.bean.override.mockito.MockitoBean;



//4 Inyección automática del cliente de pruebas web

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;



//5 Métodos estáticos de Mockito

import static org.mockito.Mockito.*;



//6 Métodos para construir peticiones HTTP simuladas y verificar respuestas

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



//7 Librería para convertir objetos a JSON (necesaria en peticiones POST)

import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.List;

@WebMvcTest(UserController.class)

public class UsuarioControllerTest {

    //Inyectando Mock para utilizar dentro de esta clase
    @Autowired
    private MockMvc mockMvc;


    @MockitoBean
    private UserService service;

    //Propiedad específica para convertir de Json a Txt y vicevera
    private final ObjectMapper mapper = new ObjectMapper();



    @Test
    @DisplayName("TESTING 1 GET")
    void testGetAll() throws Exception {
        //1 Simulación que el servicio me entrega o retorna una lista
        when(service.listar()).thenReturn(List.of(new User(1L, "BASTI", "b@duoc.cl", "GERENTE")));

        //2 Simular una petición al EndPoint
        mockMvc.perform(get("/api/v1/usuarios")) //Ruta global de mi API
                //2 Indico que es lo que espero como respuesta
                .andExpect(status().isOk()) //Tipo 200

                //3. Verificamos la coincidencia
                .andExpect(jsonPath("$[0].name").value("BASTI")); //0 indica la posición de la lista
    }


//GUARDAR Petición de tipo POST

    @Test
    @DisplayName("TESTING 2 POST")
    void testPost() throws Exception {
        //1 Simulación Guardar un juego con su ID
        User v = new User(null, "BASTI", "b@duoc.cl", "GERENTE"); //Variable de tipo local

        //2 Simular el guardado de un videojuego y su respuesta
        when(service.save(any())).thenReturn(new User(1L, "BASTI", "b@duoc.cl", "GERENTE"));

        //3 Ejecutar una petición POST y hacer la conversión de datos Cast Casteo o Parseo
        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType("application/json")  //Indicacndo que es en formato JSON
                        .content(mapper.writeValueAsString(v))) //Conviertiendo el objeto o dato
                //4 Verificamos la coincidencia
                .andExpect(status().isOk()) //Indicamos la respuesta esperada 200
                .andExpect(jsonPath("$.name").value("BASTI"));
    }

}