package com.example.vestizar.aplicacion.controlador;

import com.example.vestizar.aplicacion.Dto.UserDto;
import com.example.vestizar.aplicacion.Enums.tipoCiudad;
import com.example.vestizar.aplicacion.entidad.Usuario;
import com.example.vestizar.aplicacion.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * clase Controlador de Usuario
 */
@Controller
public class UsuarioControlador {


    @Autowired
    UsuarioServicio servicio;

    /**
     *Se crea los usuarios, se prepara el registro con un nuevo usuario para que sea rellenado en el form con los datos solicitados
     *
     * @param modelo permite trabajar con onjetos en los html en este caso estamos trabajando con Usuario para poder asignarle sus atributos.
     * @return retorna el html de registro en donde se encuentra el from para crear un nuevo usuario
     */
    /*@GetMapping("/crearUsuario")
    public String ingresoAFormUsuario(Model modelo){
    modelo.addAttribute("usuario",new Usuario());
    modelo.addAttribute("ciudad", tipoCiudad.values());
    return "registro";
    }*/

    /**
     * Sirve para guardar un usuario en la base de datos que haya sido rellenado en el form de registro
     *
     * @param usuario es el usuario que se quiere guardar en la base de datos.
     * @return redirecciona a la pagina principal.
     */
    /*@PostMapping("/guardarUsuario")
    public String guardarUsuario(UserDto usuario){
        servicio.crearNuevoUsuario(usuario);
        return "redirect:/";

    }*/
    @GetMapping("/login")
    public String login(){
        return "login";
    }








}
