package com.example.vestizar.controlador;

import com.example.vestizar.entidad.Producto;
import com.example.vestizar.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductoAdminControlador {

    @Autowired
    ProductoServicio servicio;


    @GetMapping("/inicioAdmin")
    public String busquedaAdmin(Model model) {

        List<Producto> productos = servicio.obtenerProductoPorAprobado(0);
        model.addAttribute("producto", productos);
        return "inicioAdmin";
    }

    @GetMapping("/verProductoAdmin/{id}")
    public String mostrarProductoAdmin(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("producto", servicio.obtenerProductoPorId(id));
        return "revisionAdmin";
    }

    @GetMapping("/aprobar/{id}")
    public String aprobar(@PathVariable Long id, @ModelAttribute Producto producto) {
        Producto productoExsistente=servicio.obtenerProductoPorId(id);
        productoExsistente.setIdPoroducto(id);
        productoExsistente.setAprobado(1);
        servicio.aprobar(productoExsistente);
        return "redirect:/inicioAdmin";
    }



}


