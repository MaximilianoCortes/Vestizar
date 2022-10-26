package com.example.vestizar.controlador;

import com.example.vestizar.Enums.tipoProducto;
import com.example.vestizar.Enums.tipoTalla;
import com.example.vestizar.entidad.Producto;
import com.example.vestizar.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        return "redirect:/iniciado";
    }


    @GetMapping("/busqueda")
    public String busqueda(Model model){
        List<Producto> productos=servicio.listar();
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/verProducto/{id}")
    public String mostrarProducto(@PathVariable Long id, Model modelo){
        modelo.addAttribute("producto",servicio.obtenerProductoPorId(id));
        return "articulo";

    }


}
