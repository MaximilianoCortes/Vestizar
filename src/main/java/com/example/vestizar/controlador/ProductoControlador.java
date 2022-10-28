package com.example.vestizar.controlador;

import com.example.vestizar.Enums.tipoProducto;
import com.example.vestizar.Enums.tipoTalla;
import com.example.vestizar.entidad.Producto;
import com.example.vestizar.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ProductoControlador {

    @Autowired
    ProductoServicio servicio;

    @GetMapping("/iniciado")
    public String iniciadoSesion(Model model){
        model.addAttribute("tipoProducto", tipoProducto.values());
        model.addAttribute("talla", tipoTalla.values());
        return "iniciadoSesion";
    }

    @PostMapping("/guardarProducto")
    public  String guardarProducto(@RequestParam("file") MultipartFile file,
                                   @RequestParam("tipoProducto") String tipoDeProducto,
                                   @RequestParam("estilo") String estilo,
                                   @RequestParam("estado")String estado,
                                   @RequestParam("talla")String talla ,
                                   @RequestParam("descripcion")String descripcion ,
                                   @RequestParam("color")String color ,
                                   @RequestParam("marca")String marca ,
                                   @RequestParam("precio")double precio ){

        servicio.crearNuevoProducto(file,tipoDeProducto,estilo,estado,talla,descripcion,color,marca,precio);

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
