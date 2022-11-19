package com.example.vestizar;

import com.example.vestizar.entidad.Producto;
import com.example.vestizar.servicio.ProductoServicio;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductoServicioTest {

    static Producto producto1,producto2,producto3;
    static Long IdProducto1, IdProducto2, IdProducto3;
    static List<Producto> productos;

    @Autowired
    private ProductoServicio productoServicio;

    @BeforeEach
    void setup() {
        producto1 = new Producto(0,"Polera","Hombre","Semi-Nuevo","M","Camisa con poco uso negra marca Maui","Maui",5000);
        productoServicio.guardarProducto(producto1);
        IdProducto1 = producto1.getIdPoroducto();
        producto2 = new Producto(0,"Pantalon","Mujer","Usado","S","Pantalon Levi's usado","Levi's",10000);
        productoServicio.guardarProducto(producto2);
        IdProducto2 = producto2.getIdPoroducto();
        producto3 = new Producto(1,"Poleron","Niños","Nuevo","XL","Poleron gris nuevo para niños marca Nomade","Nomade",7000);
        productoServicio.guardarProducto(producto3);
        IdProducto3 = producto3.getIdPoroducto();
        productos = Arrays.asList(producto1, producto2, producto3);
    }

    @AfterAll
    static void tearDown() {
        productos = new ArrayList<>();
        producto1 = null;
        producto2 = null;
        producto3 = null;
    }


    @AfterEach
    void teardown() {
        productoServicio.eliminarProducto(IdProducto1);
        productoServicio.eliminarProducto(IdProducto2);
        productoServicio.eliminarProducto(IdProducto3);
    }

    @DisplayName("Obtener producto por ID")
    @Test
    public void obtenerProductoPorId(){

        for (Producto producto : productos) {

            Producto productoEncontrado = productoServicio.obtenerProductoPorId(producto.getIdPoroducto());
            if(productoEncontrado != null){
                Assertions.assertNotNull(productoEncontrado);
                System.out.println("Producto "+productoEncontrado.getIdPoroducto()+" encontrado");
                Assertions.assertNull(productoEncontrado.getImagen1());
                Assertions.assertNotEquals(0, (long) productoEncontrado.getIdPoroducto());
                if(productoEncontrado.getIdPoroducto() == IdProducto1) {
                    Assertions.assertEquals(IdProducto1, productoEncontrado.getIdPoroducto());
                }else if(productoEncontrado.getIdPoroducto() == IdProducto2) {
                    Assertions.assertEquals(IdProducto2, productoEncontrado.getIdPoroducto());
                }
                else if(productoEncontrado.getIdPoroducto() == IdProducto3) {
                    Assertions.assertEquals(IdProducto3, productoEncontrado.getIdPoroducto());
                }
            }
        }



    }

    @DisplayName("Guardar producto en repositorio")
    @Test
    public void guardarProducto(){

        for (Producto producto : productos) {
            Producto productoEncontrado = productoServicio.obtenerProductoPorId(producto.getIdPoroducto());

            if(productoEncontrado != null){
                Assertions.assertNotNull(productoEncontrado);
                Assertions.assertNotEquals(0, (long) productoEncontrado.getIdPoroducto());
                Assertions.assertFalse(productoEncontrado.getIdPoroducto() <= 0);
                System.out.println("Producto "+productoEncontrado.getIdPoroducto()+" encontrado a partir de la busqueda del producto "+producto.getIdPoroducto());
                Assertions.assertEquals(producto.getIdPoroducto(), productoEncontrado.getIdPoroducto());
                Assertions.assertEquals(producto.getAprobado(), productoEncontrado.getAprobado());
                Assertions.assertEquals(producto.getTipoProducto(), productoEncontrado.getTipoProducto());
                Assertions.assertEquals(producto.getCategoria(), productoEncontrado.getCategoria());
                Assertions.assertEquals(producto.getEstado(), productoEncontrado.getEstado());
                Assertions.assertEquals(producto.getTalla(), productoEncontrado.getTalla());
                Assertions.assertEquals(producto.getDescripcion(), productoEncontrado.getDescripcion());
                Assertions.assertEquals(producto.getMarca(), productoEncontrado.getMarca());
                Assertions.assertEquals(producto.getPrecio(), productoEncontrado.getPrecio());

            }
        }


    }

    @DisplayName("Obtener lista de productos aprobados")
    @Test
    public void obtenerProductoPorAprobado(){
        List<Producto> listaProductosAprobados = productoServicio.obtenerProductoPorAprobado(1);
        Assertions.assertNotNull(listaProductosAprobados);
        Assertions.assertNotEquals(0,listaProductosAprobados.size());
        Assertions.assertTrue(listaProductosAprobados.stream().allMatch(p -> Objects.equals(p.getAprobado(), 1)));
        Assertions.assertFalse(listaProductosAprobados.stream().anyMatch(p -> Objects.equals(p.getAprobado(), 0)));

    }






}
