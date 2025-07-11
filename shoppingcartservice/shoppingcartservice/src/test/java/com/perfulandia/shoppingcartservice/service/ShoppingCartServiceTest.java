package com.perfulandia.shoppingcartservice.service;
//Importar módulos de testing
import com.perfulandia.shoppingcartservice.model.CarritoCompra;
import com.perfulandia.shoppingcartservice.model.Producto;
import com.perfulandia.shoppingcartservice.repository.CartRepository;
import jakarta.persistence.Id;
import org.junit.jupiter.api.BeforeEach; //Para ejecutar un código (métodos del servicio) antes de cada test

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test; //Nos va a permitir ejecutar pruebas de testing unitarias dentro de esta clase
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Arrays;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*; //Importando métodos de aserción (AssertEquals, assertThrows)

/**
 * Clase para probar los métodos del servicio de calculadora
 * **/

public class ShoppingCartServiceTest {

    @Mock
    private CartRepository repo; // Simulamos el repositorio

    @Mock
    private RestTemplate restTemplate; // Mock para RestTemplate

    @InjectMocks
    private CartService cartService; // El servicio que vamos a probar

    @BeforeEach
    void setUp() {
        // Inicializa los mocks antes de cada prueba
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Prueba del método listar CarritoCompra")
    void testListar() {
        // Preparación (Arrange)
        // Creamos datos de prueba
        CarritoCompra carritocompra1 = new CarritoCompra(1L, 1, 1);
        CarritoCompra carritoCompra2 = new CarritoCompra(2L, 2, 2);
        List<CarritoCompra> carts = Arrays.asList(carritocompra1, carritoCompra2);

        // Configuramos el mock para que devuelva nuestros datos de prueba
        when(repo.findAll()).thenReturn(carts);

        // Ejecución (Act)
        List<CarritoCompra> cartsFinal = cartService.listar();

        // Verificación (Assert)
        // Comprobamos que el tamaño de la lista es correcto
        assertEquals(carts.size(), cartsFinal.size());

        // Comprobamos que los usuarios devueltos son los esperados
        assertEquals(carts, cartsFinal);

        // Verificamos que se llamó al método del repositorio
        verify(repo, times(1)).findAll();
    }

    @Test
    @DisplayName("Prueba del método guardar carrito - Producto existente")
    void testSave() {
        // 1. Preparación (Arrange)
        Long productoId = 1L;
        int cantidad = 1;

        CarritoCompra carritoParaGuardar = new CarritoCompra(1L, 1, cantidad);
        CarritoCompra carritoGuardado = new CarritoCompra(1L, 1, cantidad);

        // Producto mock con stock suficiente
        Producto productoMock = new Producto(productoId, "Producto de prueba", 100.0, 10); // 10 unidades en stock

        // Configura el mock del repositorio
        when(repo.save(carritoParaGuardar)).thenReturn(carritoGuardado);

        // Configura el mock de RestTemplate para devolver el producto mock
        when(restTemplate.getForObject(
                "http://localhost:8082/api/v1/productos/" + productoId,
                Producto.class))
                .thenReturn(productoMock);

        // 2. Ejecución (Act)
        CarritoCompra resultado = cartService.save(carritoParaGuardar);

        // 3. Verificación (Assert)
        assertNotNull(resultado.getId());
        assertEquals(carritoGuardado, resultado);
        verify(repo, times(1)).save(carritoParaGuardar);

        // Verifica que se llamó a RestTemplate para validar el producto
        verify(restTemplate, times(1))
                .getForObject("http://localhost:8082/api/v1/productos/" + productoId, Producto.class);
    }
    @Test
    @DisplayName("Prueba del método guardar carrito - Producto no existe")
    void testSaveWhenProductNotExists() {
        Long productoId = 1L;
        CarritoCompra carrito = new CarritoCompra(1L, 1, 1);

        // Configura RestTemplate para devolver null (producto no existe)
        when(restTemplate.getForObject(
                "http://localhost:8082/api/v1/productos/" + productoId,
                Producto.class))
                .thenReturn(null);

        // Verifica que se lanza la excepción
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cartService.save(carrito);
        });

        assertEquals("Producto no encontrado", exception.getMessage());
        verify(repo, never()).save(any());
    }


}