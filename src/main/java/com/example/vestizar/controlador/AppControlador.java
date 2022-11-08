package com.example.vestizar.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    @GetMapping("/")
    public String inicio(){
        return "index";
    }



}

