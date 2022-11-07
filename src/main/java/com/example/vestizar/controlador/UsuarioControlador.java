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

    @GetMapping("/crearUsuario")
    public String ingresoAFormUsuario(Model modelo){
    modelo.addAttribute("usuario",new Usuario());
    modelo.addAttribute("ciudad", tipoCiudad.values());
    return "registro";
    }


    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@Valid Usuario usuario, SessionStatus status) {

        usuario.setContrasena(servicio.cifrarContrasena(usuario.getContrasena()));

        usuario.setRoles("ROLE_USER");

        servicio.crearNuevoUsuario(usuario);
        status.setComplete();
        return "iniciadoSesion";
    }








}
