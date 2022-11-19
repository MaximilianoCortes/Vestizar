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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductoServicioTest {

    static Producto producto1,producto2,producto3;
    static Long IdProducto1, IdProducto2, IdProducto3;
    static List<Producto> productos;

    @Autowired
    private ProductoServicio productoServicio;

    @BeforeAll
    static void setup() {
        producto1 = new Producto(0,"Polera","Hombre","Semi-Nuevo","M","Camisa con poco uso negra marca Maui","Maui",5000);
        IdProducto1 = producto1.getIdPoroducto();
        producto2 = new Producto(0,"Pantalon","Mujer","Usado","S","Pantalon Levi's usado","Levi's",10000);
        IdProducto2 = producto2.getIdPoroducto();
        producto3 = new Producto(1,"Poleron","Niños","Nuevo","XL","Poleron gris nuevo para niños marca Nomade","Nomade",7000);
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

    @DisplayName("Obtener producto por ID")
    @Test
    public void obtenerProductoPorId(){
        productoServicio.guardarProducto(producto1);
        productoServicio.guardarProducto(producto2);
        productoServicio.guardarProducto(producto3);

        for (Producto producto : productos) {
            if(producto != null){
                Assertions.assertNotNull(producto);
                System.out.println("Producto "+producto.getIdPoroducto()+" encontrado");
                Assertions.assertNull(producto.getImagen1());
                Assertions.assertNotEquals(0, (long) producto.getIdPoroducto());
                if(producto.getIdPoroducto() == IdProducto1) {
                    Assertions.assertEquals(IdProducto1, producto.getIdPoroducto());
                }else if(producto.getIdPoroducto() == IdProducto2) {
                    Assertions.assertEquals(IdProducto2, producto.getIdPoroducto());
                }
                else if(producto.getIdPoroducto() == IdProducto3) {
                    Assertions.assertEquals(IdProducto3, producto.getIdPoroducto());
                }
            }
        }

        productoServicio.eliminarProducto(producto1.getIdPoroducto());
        productoServicio.eliminarProducto(producto2.getIdPoroducto());
        productoServicio.eliminarProducto(producto3.getIdPoroducto());


    }

    @DisplayName("Guardar producto en repositorio")
    @Test
    public void guardarProducto(){

        for (Producto productoGuardar: productos) {
            productoServicio.guardarProducto(productoGuardar);
        }
        for (Producto productoEncontrar: productos){
            Producto productoEncontrado = productoServicio.obtenerProductoPorId(productoEncontrar.getIdPoroducto());

            if(productoEncontrado != null){
                Assertions.assertNotNull(productoEncontrado);
                Assertions.assertNotEquals(0, (long) productoEncontrado.getIdPoroducto());
                Assertions.assertFalse(productoEncontrado.getIdPoroducto() <= 0);
                System.out.println("Producto "+productoEncontrado.getIdPoroducto()+" encontrado a partir de la busqueda del producto "+productoEncontrar.getIdPoroducto());
                Assertions.assertEquals(productoEncontrar.getIdPoroducto(), productoEncontrado.getIdPoroducto());
                Assertions.assertEquals(productoEncontrar.getAprobado(), productoEncontrado.getAprobado());
                Assertions.assertEquals(productoEncontrar.getTipoProducto(), productoEncontrado.getTipoProducto());
                Assertions.assertEquals(productoEncontrar.getCategoria(), productoEncontrado.getCategoria());
                Assertions.assertEquals(productoEncontrar.getEstado(), productoEncontrado.getEstado());
                Assertions.assertEquals(productoEncontrar.getTalla(), productoEncontrado.getTalla());
                Assertions.assertEquals(productoEncontrar.getDescripcion(), productoEncontrado.getDescripcion());
                Assertions.assertEquals(productoEncontrar.getMarca(), productoEncontrado.getMarca());
                Assertions.assertEquals(productoEncontrar.getPrecio(), productoEncontrado.getPrecio());

            }

            productoServicio.eliminarProducto(productoEncontrado.getIdPoroducto());
        }

    }




}
