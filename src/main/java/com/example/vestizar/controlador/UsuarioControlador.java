package com.example.vestizar.controlador;

import com.example.vestizar.entidad.Ciudad;
import com.example.vestizar.entidad.Producto;
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
    public String ingresoAFormUsuario(Model modelo, Model modelo1){
    modelo.addAttribute("usuario",new Usuario());
    List<Ciudad> ciudades=servicioCiudad.listarCiudad();
    modelo1.addAttribute("ciudad", ciudades);
    return "registro";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(Usuario usuario){
        servicio.crearNuevoUsuario(usuario);
        return "redirect:/";

    }








}
