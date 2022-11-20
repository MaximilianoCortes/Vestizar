package com.example.vestizar.controlador;

import com.example.vestizar.Dto.UserDto;
import com.example.vestizar.Enums.*;
import com.example.vestizar.entidad.Producto;
import com.example.vestizar.entidad.Usuario;
import com.example.vestizar.servicio.ProductoServicio;
import com.example.vestizar.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * clase Controlador de la App.
 */
@Controller
public class AppControlador {

    /**
     * Ventana inicial por defecto de la pagina.
     *
     * @return la ventana index del html
     */

    private UsuarioServicio userService;

    @Autowired
    ProductoServicio servicioProducto;

    public AppControlador(UsuarioServicio userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String inicio(){
        return "index";
    }

    @GetMapping("/crearUsuario")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("usuario", user);
        model.addAttribute("ciudad", tipoCiudad.values());
        return "registro";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/guardarUsuario")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        Usuario existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "Ya hay una cuenta con ese correo");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/registro";
        }

        userService.crearNuevoUsuario(userDto);
        return "redirect:/";
    }
    @GetMapping("/iniciadoSesion")
    public String iniciadoSesion(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: "+sesion.toString());
        modelo.addAttribute("sesion",sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());

        return "iniciadoSesion";
    }


    //Desde aquí está todo iniciado :)

    @GetMapping("/iniciadoSesion/busqueda")
    public String iniciadoSesionBusqueda(Model modelo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        List<Producto> productos = servicioProducto.obtenerProductoPorAprobado(1);
        modelo.addAttribute("producto", productos);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());

        return "busquedaIniciada";


    }


    @GetMapping("iniciadoSesion/verProducto/{id}")
    public String mostrarProductoIniciadoSesion(@PathVariable Long id, Model modelo) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: "+sesion.toString());
        modelo.addAttribute("sesion",sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());


        modelo.addAttribute("producto", servicioProducto.obtenerProductoPorId(id));
        modelo.addAttribute("sesionIniciada",userService.obtenerUsuarioPorId(servicioProducto.obtenerIdVendedorProducto(id)));

        return "articuloIniciado";

    }

    @GetMapping("/iniciadoSesion/perfilVendedor/{id}")
    public String busquedaPublicacionesUsuario(@PathVariable Long id,Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: "+sesion.toString());
        modelo.addAttribute("sesionIniciada",sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());

        List<Producto> productos=servicioProducto.obtenerProductoPorVendedor(1,id);
        modelo.addAttribute("sesion",userService.obtenerUsuarioPorId(id));
        modelo.addAttribute("producto",productos);
        return "perfilVendedorIniciado";

    }

    @GetMapping("/iniciadoSesion/verMiPerfil")
    public String verMiPerfil(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: "+sesion.toString());
        modelo.addAttribute("sesion",sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());

        modelo.addAttribute("sesion",sesion);


        return "verMiPerfil";
    }

    @GetMapping("/iniciadoSesion/misPublicaciones/{id}")
    public String misPublicaciones(@PathVariable Long id,Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: "+sesion.toString());
        modelo.addAttribute("sesionIniciada",sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());

        List<Producto> productos=servicioProducto.obtenerProductoPorVendedor(1,id);
        modelo.addAttribute("sesion",userService.obtenerUsuarioPorId(id));
        modelo.addAttribute("producto",productos);

        return "misPublicaciones";

    }



    @GetMapping("/iniciadoSesion/busqueda/porTipo/polera")
    public String busquedaPoleras(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorTipoProducto(1,"Polera");
        modelo.addAttribute("producto",productos);

        return "busquedaIniciada";
    }

    /**
     * Filtra los tipos de publicaciones a solo las que sean del tipo camisas.
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos
     * @return El html busqueda en donde se mostraran todos los productos que sean del tipo camisa
     */
    @GetMapping("/iniciadoSesion/busqueda/porTipo/camisa")
    public String busquedaCamisas(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());

        List<Producto> productos= servicioProducto.obteneProductoPorTipoProducto(1,"Camisa");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }










    /**
     * Filtra los tipos de publicaciones a solo las que sean del tipo pantalones.
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos
     * @return El html busqueda en donde se mostraran todos los productos que sean del tipo pantalon
     */
    @GetMapping("/iniciadoSesion/busqueda/porTipo/pantalon")
    public String busquedaPantalones(Model modelo){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());

        List<Producto> productos= servicioProducto.obteneProductoPorTipoProducto(1,"Pantalon");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Filtra los tipos de publicaciones a solo las que sean del tipo poleron.
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html busqueda en donde se mostraran todos los productos que sean del tipo poleron.
     */
    @GetMapping("/iniciadoSesion/busqueda/porTipo/poleron")
    public String busquedaPolerones(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorTipoProducto(1,"Poleron");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }



    /**
     * Muestra tipos de productos los cuales van a estar filtrados y se mostraran solo los que estén en la categoría hombre.
     *
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría hombre
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/hombre")
    public String busquedaPorCategoríaHombre(Model modelo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());

        return "inicioCategoriaHombreIniciado";
    }

    /**
     * Muestra tipos de productos los cuales van a estar filtrados y se mostraran solo los que estén en la categoría mujer.
     *
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/mujer")
    public String busquedaPorCategoríaMujer(Model modelo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        return "inicioCategoriaMujerIniciado";
    }

    /**
     * Muestra tipos de productos los cuales van a estar filtrados y se mostraran solo los que estén en la categoría niño.
     *
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría niño.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/nino")
    public String busquedaPorCategoríaNino(Model modelo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        return "inicioCategoriaNinoIniciado";
    }


    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría hombre y del tipo polera
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría hombre y del tipo polera.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/hombre/porTipo/polera")
    public String busquedaPorCategoriaHYTipoPolera(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Polera");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría hombre y del tipo poleron
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría hombre y del tipo poleron.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/hombre/porTipo/poleron")
    public String busquedaPorCategoriaHYTipoPoleron(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Poleron");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría hombre y del tipo camisa
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría hombre y del tipo camisa.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/hombre/porTipo/camisa")
    public String busquedaPorCategoriaHYTipoCamisa(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Camisa");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría hombre y del tipo pantalón
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría hombre y del tipo pantalón.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/hombre/porTipo/pantalon")
    public String busquedaPorCategoriaHYTipoPantalon(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Pantalon");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría hombre y del tipo zapato
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría hombre y del tipo zapato.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/hombre/porTipo/zapato")
    public String busquedaPorCategoriaHYTipoZapato(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Zapato");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría hombre y del tipo chaqueta
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría hombre y del tipo chaqueta.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/hombre/porTipo/chaqueta")
    public String busquedaPorCategoriaHYTipoChaqueta(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Chaqueta");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }


    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo polera
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo polera.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/mujer/porTipo/polera")
    public String busquedaPorCategoriaMYTipoPolera(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Polera");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo poleron
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo poleron.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/mujer/porTipo/poleron")
    public String busquedaPorCategoriaMYTipoPoleron(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Poleron");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo camisa
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo camisa.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/mujer/porTipo/camisa")
    public String busquedaPorCategoriaMYTipoCamisa(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Camisa");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo pantalón
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo pantalón.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/mujer/porTipo/pantalon")
    public String busquedaPorCategoriaMYTipoPantalon(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Pantalon");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo zapato
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo zapato.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/mujer/porTipo/zapato")
    public String busquedaPorCategoriaMYTipoZapato(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Zapato");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo chaqueta
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo chaqueta.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/mujer/porTipo/chaqueta")
    public String busquedaPorCategoriaMYTipoChaqueta(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Chaqueta");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo blusa.
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo blusa.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/mujer/porTipo/blusa")
    public String busquedaPorCategoriaMYTipoBlusa(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Blusa");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría mujer y del tipo falda.
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría mujer y del tipo falda.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/mujer/porTipo/falda")
    public String busquedaPorCategoriaMYTipoFalda(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Falda");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría niño y del tipo polera.
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría niño y del tipo polera.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/nino/porTipo/polera")
    public String busquedaPorCategoriaNYTipoPolera(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Polera");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría niño y del tipo poleron.
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría niño y del tipo poleron.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/nino/porTipo/poleron")
    public String busquedaPorCategoriaNYTipoPoleron(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Poleron");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría niño y del tipo camisa.
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría niño y del tipo camisa.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/nino/porTipo/camisa")
    public String busquedaPorCategoriaNYTipoCamisa(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Camisa");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría niño y del tipo pantalon.
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría niño y del tipo pantalon.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/nino/porTipo/pantalon")
    public String busquedaPorCategoriaNYTipoPantalon(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Pantalon");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría niño y del tipo zapato.
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría niño y del tipo zapato.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/nino/porTipo/zapato")
    public String busquedaPorCategoriaNYTipoZapato(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Zapato");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }

    /**
     * Flitra todas las publicaciones y muestra únicamente los que sean de la categoría niño y del tipo chaqueta.
     *
     * @param modelo Se utiliza para poder trabajar objetos dentro de los html, en este caso se utilizan para poder mostrar
     *              productos y sus atributos.
     * @return El html en donde se mostraran los tipos de productos disponibles en la categoría niño y del tipo chaqueta.
     */
    @GetMapping("/iniciadoSesion/busqueda/categoria/nino/porTipo/chaqueta")
    public String busquedaPorCategoriaNYTipoChaqueta(Model modelo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        Usuario sesion = userService.findUserByEmail(usuario);
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo.addAttribute("sesion", sesion);

        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        List<Producto> productos= servicioProducto.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Chaqueta");
        modelo.addAttribute("producto",productos);
        return "busquedaIniciada";
    }








}

