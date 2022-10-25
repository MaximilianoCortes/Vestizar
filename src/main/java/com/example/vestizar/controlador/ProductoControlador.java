package com.example.vestizar.controlador;

import com.example.vestizar.Enums.tipoProducto;
import com.example.vestizar.Enums.tipoTalla;
import com.example.vestizar.entidad.Producto;
import com.example.vestizar.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductoControlador {

    @Autowired
    ProductoServicio servicio;

    @GetMapping("/iniciado")
    public String iniciadoSesion(Model model){
        model.addAttribute("producto",new Producto());
        model.addAttribute("tipoProducto", tipoProducto.values());
        model.addAttribute("talla", tipoTalla.values());
        return "iniciadoSesion";
    }

    @PostMapping("/guardarProducto")
    public  String guardarProducto(Producto producto){
        servicio.crearNuevoProducto(producto);
        return "iniciadoSesion";
    }


}
