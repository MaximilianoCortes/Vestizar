package com.example.vestizar.controlador;

import com.example.vestizar.Enums.tipoCiudad;
import com.example.vestizar.entidad.Usuario;
import com.example.vestizar.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes("usuario")
public class UsuarioControlador {

    @Autowired
    UsuarioServicio servicio;

    @GetMapping("/iniciar-sesion")
    public String iniciarSesion() {
        return "iniciadoSesion";
    }

    /**
     * Al modelo de mapping se le agregan los atributos del objeto/entidad usuario y los values del enum tipoCiudad conseguidos de la pagina web.
     * @param modelo Modelo de mapping al que se le agregaran los atributos a partir de los datos de la pagina.
     * @return Retorna la referencia html de registro que corresponde al metodo del controlador.
     */
    @GetMapping("/crearUsuario")
    public String ingresoAFormUsuario(Model modelo){
    modelo.addAttribute("usuario",new Usuario());
    modelo.addAttribute("ciudad", tipoCiudad.values());
    return "registro";
    }


    /**
     * Al usuario ingresado por parametro se le cifrara la contrasena, se le asignara el rol ROLE_USER de usuario normal y a partir del metodo de servicio se guardara
     * en la base de datos a partir del repositorio correspondiente. Su status cambia a ser completo.
     * @param usuario Usuario validado el cual se guardara en la base de datos,
     * @param status Estado de sesion del usuario.
     * @return Retorna la referencia html de iniciadoSesion que corresponde al metodo del controlador.
     */
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@Valid Usuario usuario, SessionStatus status) {

        usuario.setContrasena(servicio.cifrarContrasena(usuario.getContrasena()));

        usuario.setRoles("ROLE_USER");

        servicio.crearNuevoUsuario(usuario);
        status.setComplete();
        return "iniciadoSesion";
    }








}
