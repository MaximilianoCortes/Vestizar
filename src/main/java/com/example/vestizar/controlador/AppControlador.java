package com.example.vestizar.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppControlador {

    @GetMapping("/")
    public String inicio(){
        return "index";
    }



}

