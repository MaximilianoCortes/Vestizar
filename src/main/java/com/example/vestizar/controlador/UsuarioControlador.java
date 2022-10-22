package com.example.vestizar.controlador;

import com.example.vestizar.Enums.tipoCiudad;
import com.example.vestizar.entidad.Usuario;
import com.example.vestizar.servicio.CiudadServicio;
import com.example.vestizar.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UsuarioControlador {

    @Autowired
    UsuarioServicio servicio;

    @Autowired
    CiudadServicio servicioCiudad;


    @GetMapping("/crearUsuario")
    public String ingresoAFormUsuario(Model modelo){
    modelo.addAttribute("usuario",new Usuario());
    modelo.addAttribute("ciudad", tipoCiudad.values());
    return "registro";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(Usuario usuario){
        servicio.crearNuevoUsuario(usuario);
        return "redirect:/";

    }








}
