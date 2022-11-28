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


@Controller
public class AppControlador {


    private UsuarioServicio userService;

    @Autowired
    ProductoServicio servicioProducto;

    public AppControlador(UsuarioServicio userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String inicio() {
        return "index";
    }


    @GetMapping("/crearUsuario")
    public String showRegistrationForm(Model model) {
        // crea modelo de los datos de usuario
        UserDto user = new UserDto();
        model.addAttribute("usuario", user);
        model.addAttribute("ciudad", tipoCiudad.values());
        return "registro";
    }

    // metodo que se ejecuta con una request de submit
    @PostMapping("/guardarUsuario")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        Usuario existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "Ya hay una cuenta con ese correo");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/registro";
        }

        userService.crearNuevoUsuario(userDto);
        return "redirect:/";
    }

    @GetMapping("/iniciadoSesion")
    public String iniciadoSesion(Model modelo) {
        Usuario sesion = obtenerSesion();
        agregarAtributos(modelo, sesion);

        return "iniciadoSesion";
    }


    //Desde aquí está todo iniciado :)

    @GetMapping("/iniciadoSesion/busqueda")
    public String iniciadoSesionBusqueda(Model modelo) {
        Usuario sesion = obtenerSesion();
        agregarAtributos(modelo, sesion);

        List<Producto> productos = servicioProducto.obtenerProductoPorAprobado(1);
        modelo.addAttribute("producto", productos);


        return "busquedaIniciada";


    }

    @GetMapping("iniciadoSesion/verProducto/{id}")
    public String mostrarProductoIniciadoSesion(@PathVariable Long id, Model modelo) {

        Usuario sesion = obtenerSesion();
        agregarAtributos(modelo, sesion);


        modelo.addAttribute("producto", servicioProducto.obtenerProductoPorId(id));
        modelo.addAttribute("sesionVendedor", userService.obtenerUsuarioPorId(servicioProducto.obtenerIdVendedorProducto(id)));

        return "articuloIniciado";

    }


    @GetMapping("iniciadoSesion/verMisProductos/{id}")
    public String mostrarMisProductoIniciadoSesion(@PathVariable Long id, Model modelo) {
        Usuario sesion = obtenerSesion();
        agregarAtributos(modelo, sesion);
        modelo.addAttribute("producto", servicioProducto.obtenerProductoPorId(id));
        modelo.addAttribute("sesionIniciada", userService.obtenerUsuarioPorId(servicioProducto.obtenerIdVendedorProducto(id)));
        return "MisArticulos";
    }

    @GetMapping("/eliminarProducto/{id}")
    public String eliminarProducto(@PathVariable Long id, Model modelo) {
        servicioProducto.eliminarProducto(id);
        return "";
    }

    @GetMapping("/iniciadoSesion/perfilVendedor/{id}")
    public String busquedaPublicacionesUsuario(@PathVariable Long id, Model modelo) {
        Usuario sesion = obtenerSesion();
        agregarAtributos(modelo, sesion);

        List<Producto> productos = servicioProducto.obtenerProductoPorVendedor(1, id);
        modelo.addAttribute("sesion", userService.obtenerUsuarioPorId(id));
        modelo.addAttribute("producto", productos);
        return "perfilVendedorIniciado";

    }

    @GetMapping("/iniciadoSesion/verMiPerfil")
    public String verMiPerfil(Model modelo) {
        Usuario sesion = obtenerSesion();
        agregarAtributos(modelo, sesion);


        return "verMiPerfil";
    }

    @GetMapping("/iniciadoSesion/misPublicaciones/{id}")
    public String misPublicaciones(@PathVariable Long id, Model modelo) {
        Usuario sesion = obtenerSesion();
        agregarAtributos(modelo, sesion);

        List<Producto> productos = servicioProducto.obtenerProductoPorVendedor(1, id);
        modelo.addAttribute("sesion", userService.obtenerUsuarioPorId(id));
        modelo.addAttribute("producto", productos);

        return "misPublicaciones";

    }


    @GetMapping("/iniciadoSesion/busqueda/porTipo/{tipo}")
    public String busquedaTipo(@PathVariable("tipo") String tipo, Model modelo) {
        Usuario sesion = obtenerSesion();
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo = agregarAtributos(modelo, sesion);
        List<Producto> productos = servicioProducto.obteneProductoPorTipoProducto(1, tipo);
        modelo.addAttribute("producto", productos);
        return "busquedaIniciada";
    }


    @GetMapping("/iniciadoSesion/busqueda/categoria/{sexo}")
    public String busquedaPorCategoríaSexo(@PathVariable("sexo") String sexo, Model modelo) {
        Usuario sesion = obtenerSesion();
        agregarAtributos(modelo, sesion);


        return "inicioCategoria"+sexo+"Iniciado";
    }


    public Usuario obtenerSesion() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        return userService.findUserByEmail(usuario);
    }

    public Model agregarAtributos(Model modelo, Usuario sesion) {
        modelo.addAttribute("sesion", sesion);
        modelo.addAttribute("tipoProducto", tipoProducto.values());
        modelo.addAttribute("talla", tipoTalla.values());
        modelo.addAttribute("estado", tipoEstado.values());
        modelo.addAttribute("categoria", tipoCategoria.values());
        return modelo;
    }

    @GetMapping("/iniciadoSesion/busqueda/categoria/{sexo}/porTipo/{tipo}")
    public String busquedaPorCategoriaH(@PathVariable("tipo") String tipo, @PathVariable("sexo") String sexo, Model modelo) {
        Usuario sesion = obtenerSesion();
        System.out.println("este es el correo que inicio sesion: " + sesion.toString());
        modelo = agregarAtributos(modelo, sesion);
        List<Producto> productos = servicioProducto.obteneProductoPorCategoriaYTipoProducto(1, sexo, tipo);
        modelo.addAttribute("producto", productos);
        return "busquedaIniciada";
    }


}

