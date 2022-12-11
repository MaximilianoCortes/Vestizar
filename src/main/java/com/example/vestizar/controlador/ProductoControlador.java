package com.example.vestizar.controlador;

import com.example.vestizar.entidad.Producto;
import com.example.vestizar.servicio.ProductoServicio;
import com.example.vestizar.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * clase de Controlador para Producto
 */
@Controller
public class ProductoControlador {

    @Autowired
    ProductoServicio servicioProducto;
    @Autowired
    UsuarioServicio servicioUsuario;



    /**
     * Permite crear y guardar un producto una vez obtenido todos los datos que se solicitan para poder crearlo.
     *
     * @param file           Es la imagen que producto.
     * @param tipoDeProducto Es el tipo de producto que tendrá el nuevo producto.
     * @param categoria      Es la categoría en la que entrará el nuevo producto.
     * @param estado         Es el estado en el que se encuentra el producto.
     * @param talla          Es la talla del producto.
     * @param descripcion    Es la descripción del producto.
     * @param marca          Es la marca del producto.
     * @param precio         Es el precio del producto.
     * @return Redirecciona al inicio principal iniciado sesion y ya listo para poder crear otro producto.
     */
    @PostMapping("/guardarProducto")
    public String guardarProducto(@RequestParam("file") MultipartFile file,
                                  @RequestParam("tipoProducto") String tipoDeProducto,
                                  @RequestParam("categoria") String categoria,
                                  @RequestParam("estado") String estado,
                                  @RequestParam("talla") String talla,
                                  @RequestParam("descripcion") String descripcion,
                                  @RequestParam("marca") String marca,
                                  @RequestParam("precio") double precio,
                                  @RequestParam("idVendedor") Long idVendedor) {

        servicioProducto.crearNuevoProducto(file, tipoDeProducto, categoria,estado, talla, descripcion,marca, precio,idVendedor);

        return "redirect:/iniciadoSesion";
    }


    /**
     * Muestra todos los productos que hayan sido aprobados por los administradores.
     *
     * @param model Se utiliza para poder trabajar objetos en los html, en este caso
     *              se utiliza para poder trabajar con productos y sus atributos.
     * @return El html de busqueda donde se mostraran todos los productos que hayan sido aprobados
     */
    @GetMapping("/busqueda")
    public String busqueda(Model model) {

        List<Producto> productos = servicioProducto.obtenerProductoPorAprobado(1);
        model.addAttribute("producto", productos);
        return "busqueda";
    }

    /**
     * Sirve para poder ingresar a una publicación de un producto específico y poder ver todos los datos
     * del producto en cuestion
     *
     * @param id     Es el id asignado a cada producto, es necesario para poder acceder a ese producto
     *               en específico.
     * @param modelo  Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *                productos y sus atributos.
     * @return El html artículo en donde se van a poder mostrar los atributos del produco especifico al que se ingresó
     *
     */
    @GetMapping("/verProducto/{id}")
    public String mostrarProducto(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("producto", servicioProducto.obtenerProductoPorId(id));
        modelo.addAttribute("sesion", servicioUsuario.obtenerUsuarioPorId(servicioProducto.obtenerIdVendedorProducto(id)));
        return "articulo";

    }

    @GetMapping("/perfilVendedor/{id}")
    public String perfilVendedor(@PathVariable Long id, Model modelo){
        List<Producto> productos=servicioProducto.obtenerProductoPorVendedor(1,id);
        modelo.addAttribute("sesion",servicioUsuario.obtenerUsuarioPorId(id));
        modelo.addAttribute("producto",productos);

        return "perfilVendedor";
    }


    /**
     * Filtra los tipos de publicaciones a solo las que sean del tipo poleras
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos
     * @return El html busqueda en donde se mostraran todos los productos que sean del tipo polera
     */
    @GetMapping("/busqueda/porTipo/{tipo}")
    public String busquedaPoleras(@PathVariable("tipo") String tipo, Model model){
        List<Producto> productos= servicioProducto.obteneProductoPorTipoProducto(1,tipo);
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/{tipoCategoria}")
    public String busquedaPorCategoríaHombre(@PathVariable("tipoCategoria") String tipoCategoria) {
        return "inicioCategoria"+tipoCategoria;
    }

    @GetMapping("/busqueda/categoria/{genero}/porTipo/{tipoProducto}")
    public String busquedaPorCategoriaHYTipoPolera(@PathVariable("genero") String genero,
                                                   @PathVariable("tipoProducto") String tipoProducto,
                                                   Model model){
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,genero,tipoProducto);
        model.addAttribute("producto",productos);
        return "busqueda";
    }


}
