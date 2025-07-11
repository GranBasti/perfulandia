package com.perfulandia.productservice.service;

//Importar módulos de testing
import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.repository.ProductRepository;
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

public class ProductoServiceTest {
    @Mock
    private ProductRepository repo; // Simulamos el repositorio

    @InjectMocks
    private ProductService productService; // El servicio que vamos a probar

    @BeforeEach
    void setUp() {
        // Inicializa los mocks antes de cada prueba
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Prueba del método listar productos")
    void testListar() {
        // Preparación (Arrange)
        // Creamos datos de prueba
        Producto producto1 = new Producto(null, "KING", 180000.0, 10);
        Producto producto2 = new Producto(null, "Tommy", 800000.0, 5);
        List<Producto> productosEsperados = Arrays.asList(producto1, producto2);

        // Configuramos el mock para que devuelva nuestros datos de prueba
        when(repo.findAll()).thenReturn(productosEsperados);

        // Ejecución (Act)
        List<Producto> productosObtenidos = productService.listar();

        // Verificación (Assert)
        // Comprobamos que el tamaño de la lista es correcto
        assertEquals(productosEsperados.size(), productosObtenidos.size());

        // Comprobamos que los usuarios devueltos son los esperados
        assertEquals(productosEsperados, productosObtenidos);

        // Verificamos que se llamó al método del repositorio
        verify(repo, times(1)).findAll();
    }


    @Test
    @DisplayName("Prueba del método guardar productos")
    void testSave() {
        // 1. Preparación (Arrange)
        Producto productoParaGuardar = new Producto(null, "King", 180000.0, 10); // ID null porque se generará
        Producto productoGuardado = new Producto(null, "Tommy", 80000.0, 1); // Con ID generado

        // Configuramos el mock para que devuelva el usuario guardado
        when(repo.save(productoParaGuardar)).thenReturn(productoGuardado);

        // 2. Ejecución (Act)
        Producto resultado = productService.save(productoParaGuardar);

        // 3. Verificación (Assert)
        // assertNotNull(resultado.getId(), "El ID no debería ser nulo después de guardar");
        assertEquals(productoGuardado, resultado, "El usuario devuelto debería ser igual al guardado");
        // assertEquals("Jose", resultado.getNombre(), "El nombre debería coincidir");
        // assertEquals("jose@123.cl", resultado.getApellido(), "El correo debería coincidir");

        // Verificamos que se llamó al método save del repositorio
        verify(repo, times(1)).save(productoParaGuardar);
    }


}
