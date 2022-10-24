package com.example.vestizar.aplicacion.controlador;

import com.example.vestizar.aplicacion.servicio.CiudadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CiudadControlador {

    @Autowired
    private CiudadServicio servicio;



}
