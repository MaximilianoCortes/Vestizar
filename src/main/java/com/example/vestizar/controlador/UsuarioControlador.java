package com.example.vestizar.controlador;

import com.example.vestizar.entidad.Producto;
import com.example.vestizar.entidad.Usuario;
import com.example.vestizar.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioControlador {

    @Autowired
    UsuarioServicio servicio;


    @GetMapping("/crearUsuario")
    public String ingresoAFormUsuario(Model modelo){
    modelo.addAttribute("usuario",new Usuario());
    return "registro";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(Usuario usuario){
        servicio.crearNuevoUsuario(usuario);
        return "redirect:/";

    }








}
