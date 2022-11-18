package com.example.vestizar.aplicacion.controlador;

import com.example.vestizar.aplicacion.Dto.UserDto;
import com.example.vestizar.aplicacion.Enums.tipoCiudad;
import com.example.vestizar.aplicacion.entidad.Usuario;
import com.example.vestizar.aplicacion.servicio.UsuarioServicio;
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



}

