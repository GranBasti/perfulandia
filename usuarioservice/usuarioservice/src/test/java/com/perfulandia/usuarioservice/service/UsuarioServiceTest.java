package com.perfulandia.usuarioservice.service;


//Importar módulos de testing
import com.perfulandia.usuarioservice.modelo.User;
import com.perfulandia.usuarioservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach; //Para ejecutar un código (métodos del servicio) antes de cada test

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test; //Nos va a permitir ejecutar pruebas de testing unitarias dentro de esta clase
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Arrays;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*; //Importando métodos de aserción (AssertEquals, assertThrows)

/**
 * Clase para probar los métodos del servicio de calculadora
 * **/

public class UsuarioServiceTest {
    @Mock
    private UserRepository repo; // Simulamos el repositorio

    @InjectMocks
    private UserService userService; // El servicio que vamos a probar

    @BeforeEach
    void setUp() {
        // Inicializa los mocks antes de cada prueba
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Prueba del método listar usuarios")
    void testListar() {
        // Preparación (Arrange)
        // Creamos datos de prueba
        User usuario1 = new User(1L, "BASTI", "b@duoc.cl", "GERENTE");
        User usuario2 = new User(2L, "Gran Basti", "bas@duoc.cl", "Asistente de laboratorio CTA");
        List<User> usuariosEsperados = Arrays.asList(usuario1, usuario2);

        // Configuramos el mock para que devuelva nuestros datos de prueba
        when(repo.findAll()).thenReturn(usuariosEsperados);

        // Ejecución (Act)
        List<User> usuariosObtenidos = userService.listar();

        // Verificación (Assert)
        // Comprobamos que el tamaño de la lista es correcto
        assertEquals(usuariosEsperados.size(), usuariosObtenidos.size());

        // Comprobamos que los usuarios devueltos son los esperados
        assertEquals(usuariosEsperados, usuariosObtenidos);

        // Verificamos que se llamó al método del repositorio
        verify(repo, times(1)).findAll();
    }


    @Test
    @DisplayName("Prueba del método guardar usuarios")
    void testSave() {
        // 1. Preparación (Arrange)
        User usuarioParaGuardar = new User(null, "BASTI", "b@duoc.cl", "GERENTE"); // ID null porque se generará
        User usuarioGuardado = new User(null, "BASTI", "b@duoc.cl", "GERENTE"); // Con ID generado

        // Configuramos el mock para que devuelva el usuario guardado
        when(repo.save(usuarioParaGuardar)).thenReturn(usuarioGuardado);

        // 2. Ejecución (Act)
        User resultado = userService.save(usuarioParaGuardar);

        // 3. Verificación (Assert)
        // assertNotNull(resultado.getId(), "El ID no debería ser nulo después de guardar");
        assertEquals(usuarioGuardado, resultado, "El usuario devuelto debería ser igual al guardado");
        // assertEquals("Jose", resultado.getNombre(), "El nombre debería coincidir");
        // assertEquals("jose@123.cl", resultado.getApellido(), "El correo debería coincidir");

        // Verificamos que se llamó al método save del repositorio
        verify(repo, times(1)).save(usuarioParaGuardar);
    }




}