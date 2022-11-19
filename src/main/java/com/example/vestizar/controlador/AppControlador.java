package com.example.vestizar.controlador;

import com.example.vestizar.Dto.UserDto;
import com.example.vestizar.Enums.*;
import com.example.vestizar.entidad.Usuario;
import com.example.vestizar.servicio.UsuarioServicio;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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

    //a partir de aca todo es con sesion iniciada
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
}

