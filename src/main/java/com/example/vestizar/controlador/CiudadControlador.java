package com.example.vestizar.controlador;

import com.example.vestizar.servicio.CiudadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CiudadControlador {

    @Autowired
    private CiudadServicio servicio;



}
