package com.example.vestizar.controlador;

import com.example.vestizar.entidad.Producto;
import com.example.vestizar.entidad.Usuario;
import com.example.vestizar.servicio.ProductoServicio;
import com.example.vestizar.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * clase Controlador de los productos mostrados en Administrador
 */
@Controller
public class ProductoAdminControlador {

    @Autowired
    ProductoServicio servicioProducto;
    @Autowired
    UsuarioServicio servicioUsuario;


    /**
     * Se muestran todos los productos que aun no hayan sido aprobados por ningún administrador.
     *
     * @param model se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html de inicioAdmin que es la pagina principal de administrador.
     */
    @GetMapping("/inicioAdmin")
    public String busquedaAdmin(Model model) {

        List<Producto> productos = servicioProducto.obtenerProductoPorAprobado(0);
        model.addAttribute("producto", productos);
        return "inicioAdmin";
    }

    /**
     * Permite ingresar a un producto especifico de toda una lista para poder revisar
     * sus atributos individualmente.
     *
     * @param id Es el id asignado a cada producto, es necesario para poder acceder a ese producto
     *           en especifico.
     * @param modelo se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *               productos y sus atributos.
     * @return El html revisionAdmin en donde se van a poder mostrar los atributos del produco al que se ingreso y
     * verificar que esté todo correcto.
     */
    @GetMapping("/verProductoAdmin/{id}")
    public String mostrarProductoAdmin(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("producto", servicioProducto.obtenerProductoPorId(id));
        modelo.addAttribute("sesion", servicioUsuario.obtenerUsuarioPorId(servicioProducto.obtenerIdVendedorProducto(id)));
        return "revisionAdmin";
    }

    /**
     * Permite al administrador aprobar publicaciones lo que hará que las que se hayan
     * aprobado se muestren en las busquedas normales.
     *
     * @param id Es el id asignado a cada producto sirve para obtener el producto que se quiere aprobar.
     * @param producto se utiliza para poder buscar el id especifico del producto y tambien permite que se pueda
     *                 modificar el valor de aprobado del producto actual, este pasa de 0 a 1.
     * @return redirecciona hacia inicioAdmin donde están todos los productos no aprobados.
     */
    @GetMapping("/aprobar/{id}")
    public String aprobar(@PathVariable Long id, @ModelAttribute Producto producto) {
        Producto productoExsistente=servicioProducto.obtenerProductoPorId(id);
        productoExsistente.setIdPoroducto(id);
        productoExsistente.setAprobado(1);
        servicioProducto.guardarProducto(productoExsistente);
        return "redirect:/inicioAdmin";
    }

    /**
     * Permite a administradores rechazar un producto que no haya sido aprobado, lo que hará que este se elimine.
     *
     * @param id Es el id asignado a todos los productos, gracias a el se borra el producto que esté asociado a ese id en especifico.
     * @return redirecciona hacia inicioAdmin donde están todos los productos no aprobados.
     */
    @GetMapping("/rechazar/{id}")
    public String rechazarProducto(@PathVariable Long id){
        servicioProducto.eliminarProducto(id);
        return "redirect:/inicioAdmin";


    }






}


