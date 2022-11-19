package com.example.vestizar.controlador;

import com.example.vestizar.entidad.Producto;
import com.example.vestizar.servicio.ProductoServicio;
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
    ProductoServicio servicio;



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

        servicio.crearNuevoProducto(file, tipoDeProducto, categoria,estado, talla, descripcion,marca, precio,idVendedor);

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

        List<Producto> productos = servicio.obtenerProductoPorAprobado(1);
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
        modelo.addAttribute("producto", servicio.obtenerProductoPorId(id));
        return "articulo";

    }


    /**
     * Filtra los tipos de publicaciones a solo las que sean del tipo poleras
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos
     * @return El html busqueda en donde se mostraran todos los productos que sean del tipo polera
     */
    @GetMapping("/busqueda/porTipo/polera")
    public String busquedaPoleras(Model model){
        List<Producto> productos= servicio.obteneProductoPorTipoProducto(1,"Polera");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Filtra los tipos de publicaciones a solo las que sean del tipo camisas.
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos
     * @return El html busqueda en donde se mostraran todos los productos que sean del tipo camisa
     */
    @GetMapping("/busqueda/porTipo/camisa")
    public String busquedaCamisas(Model model){
        List<Producto> productos= servicio.obteneProductoPorTipoProducto(1,"Camisa");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Filtra los tipos de publicaciones a solo las que sean del tipo pantalones.
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos
     * @return El html busqueda en donde se mostraran todos los productos que sean del tipo pantalon
     */
    @GetMapping("/busqueda/porTipo/pantalon")
    public String busquedaPantalones(Model model){
        List<Producto> productos= servicio.obteneProductoPorTipoProducto(1,"Pantalon");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Filtra los tipos de publicaciones a solo las que sean del tipo poleron.
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html busqueda en donde se mostraran todos los productos que sean del tipo poleron.
     */
    @GetMapping("/busqueda/porTipo/poleron")
    public String busquedaPolerones(Model model){
        List<Producto> productos= servicio.obteneProductoPorTipoProducto(1,"Poleron");
        model.addAttribute("producto",productos);
        return "busqueda";
    }



    /**
     * Muestra tipos de productos los cuales van a estar filtrados y se mostraran solo los que estén en la categoría hombre.
     *
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría hombre
     */
    @GetMapping("/busqueda/categoria/hombre")
    public String busquedaPorCategoríaHombre() {
        return "inicioCategoriaHombre";
    }

    /**
     * Muestra tipos de productos los cuales van a estar filtrados y se mostraran solo los que estén en la categoría mujer.
     *
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer.
     */
    @GetMapping("/busqueda/categoria/mujer")
    public String busquedaPorCategoríaMujer() {
        return "inicioCategoriaMujer";
    }

    /**
     * Muestra tipos de productos los cuales van a estar filtrados y se mostraran solo los que estén en la categoría niño.
     *
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría niño.
     */
    @GetMapping("/busqueda/categoria/nino")
    public String busquedaPorCategoríaNino() {
        return "inicioCategoriaNino";
    }


    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría hombre y del tipo polera
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría hombre y del tipo polera.
     */
    @GetMapping("/busqueda/categoria/hombre/porTipo/polera")
    public String busquedaPorCategoriaHYTipoPolera(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Polera");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría hombre y del tipo poleron
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría hombre y del tipo poleron.
     */
    @GetMapping("/busqueda/categoria/hombre/porTipo/poleron")
    public String busquedaPorCategoriaHYTipoPoleron(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Poleron");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría hombre y del tipo camisa
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría hombre y del tipo camisa.
     */
    @GetMapping("/busqueda/categoria/hombre/porTipo/camisa")
    public String busquedaPorCategoriaHYTipoCamisa(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Camisa");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría hombre y del tipo pantalón
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría hombre y del tipo pantalón.
     */
    @GetMapping("/busqueda/categoria/hombre/porTipo/pantalon")
    public String busquedaPorCategoriaHYTipoPantalon(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Pantalon");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría hombre y del tipo zapato
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría hombre y del tipo zapato.
     */
    @GetMapping("/busqueda/categoria/hombre/porTipo/zapato")
    public String busquedaPorCategoriaHYTipoZapato(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Zapato");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría hombre y del tipo chaqueta
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría hombre y del tipo chaqueta.
     */
    @GetMapping("/busqueda/categoria/hombre/porTipo/chaqueta")
    public String busquedaPorCategoriaHYTipoChaqueta(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Chaqueta");
        model.addAttribute("producto",productos);
        return "busqueda";
    }


    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo polera
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo polera.
     */
    @GetMapping("/busqueda/categoria/mujer/porTipo/polera")
    public String busquedaPorCategoriaMYTipoPolera(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Polera");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo poleron
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo poleron.
     */
    @GetMapping("/busqueda/categoria/mujer/porTipo/poleron")
    public String busquedaPorCategoriaMYTipoPoleron(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Poleron");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo camisa
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo camisa.
     */
    @GetMapping("/busqueda/categoria/mujer/porTipo/camisa")
    public String busquedaPorCategoriaMYTipoCamisa(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Camisa");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo pantalón
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo pantalón.
     */
    @GetMapping("/busqueda/categoria/mujer/porTipo/pantalon")
    public String busquedaPorCategoriaMYTipoPantalon(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Pantalon");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo zapato
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo zapato.
     */
    @GetMapping("/busqueda/categoria/mujer/porTipo/zapato")
    public String busquedaPorCategoriaMYTipoZapato(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Zapato");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo chaqueta
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo chaqueta.
     */
    @GetMapping("/busqueda/categoria/mujer/porTipo/chaqueta")
    public String busquedaPorCategoriaMYTipoChaqueta(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Chaqueta");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo blusa.
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo blusa.
     */
    @GetMapping("/busqueda/categoria/mujer/porTipo/blusa")
    public String busquedaPorCategoriaMYTipoBlusa(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Blusa");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo falda.
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo falda.
     */
    @GetMapping("/busqueda/categoria/mujer/porTipo/falda")
    public String busquedaPorCategoriaMYTipoFalda(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Falda");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría niño y del tipo polera.
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría niño y del tipo polera.
     */
    @GetMapping("/busqueda/categoria/nino/porTipo/polera")
    public String busquedaPorCategoriaNYTipoPolera(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Polera");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría niño y del tipo poleron.
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría niño y del tipo poleron.
     */
    @GetMapping("/busqueda/categoria/nino/porTipo/poleron")
    public String busquedaPorCategoriaNYTipoPoleron(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Poleron");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría niño y del tipo camisa.
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría niño y del tipo camisa.
     */
    @GetMapping("/busqueda/categoria/nino/porTipo/camisa")
    public String busquedaPorCategoriaNYTipoCamisa(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Camisa");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría niño y del tipo pantalon.
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría niño y del tipo pantalon.
     */
    @GetMapping("/busqueda/categoria/nino/porTipo/pantalon")
    public String busquedaPorCategoriaNYTipoPantalon(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Pantalon");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría niño y del tipo zapato.
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría niño y del tipo zapato.
     */
    @GetMapping("/busqueda/categoria/nino/porTipo/zapato")
    public String busquedaPorCategoriaNYTipoZapato(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Zapato");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría niño y del tipo chaqueta.
     *
     * @param model Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría niño y del tipo chaqueta.
     */
    @GetMapping("/busqueda/categoria/nino/porTipo/chaqueta")
    public String busquedaPorCategoriaNYTipoChaqueta(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Chaqueta");
        model.addAttribute("producto",productos);
        return "busqueda";
    }



}
