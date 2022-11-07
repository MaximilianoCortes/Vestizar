package com.example.vestizar.controlador;

import com.example.vestizar.Enums.tipoCategoria;
import com.example.vestizar.Enums.tipoEstado;
import com.example.vestizar.Enums.tipoProducto;
import com.example.vestizar.Enums.tipoTalla;
import com.example.vestizar.entidad.Producto;
import com.example.vestizar.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ProductoControlador {

    @Autowired
    ProductoServicio servicio;

    @GetMapping("/iniciado")
    public String iniciadoSesion(Model model) {
        model.addAttribute("tipoProducto", tipoProducto.values());
        model.addAttribute("talla", tipoTalla.values());
        model.addAttribute("estado", tipoEstado.values());
        model.addAttribute("categoria", tipoCategoria.values());
        return "iniciadoSesion";
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto(@RequestParam("file") MultipartFile file,
                                  @RequestParam("tipoProducto") String tipoDeProducto,
                                  @RequestParam("categoria") String categoria,
                                  @RequestParam("estado") String estado,
                                  @RequestParam("talla") String talla,
                                  @RequestParam("descripcion") String descripcion,
                                  @RequestParam("marca") String marca,
                                  @RequestParam("precio") double precio) {

        servicio.crearNuevoProducto(file, tipoDeProducto, categoria,estado, talla, descripcion,marca, precio);

        return "redirect:/iniciado";
    }


    @GetMapping("/busqueda")
    public String busqueda(Model model) {

        List<Producto> productos = servicio.obtenerProductoPorAprobado(1);
        model.addAttribute("producto", productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/porTipo/polera")
    public String busquedaPoleras(Model model){
        List<Producto> productos= servicio.obteneProductoPorTipoProducto(1,"Polera");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/porTipo/camisa")
    public String busquedaCamisas(Model model){
        List<Producto> productos= servicio.obteneProductoPorTipoProducto(1,"Camisa");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/porTipo/pantalon")
    public String busquedaPantalones(Model model){
        List<Producto> productos= servicio.obteneProductoPorTipoProducto(1,"Pantalon");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/porTipo/poleron")
    public String busquedaPolerones(Model model){
        List<Producto> productos= servicio.obteneProductoPorTipoProducto(1,"Poleron");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/verProducto/{id}")
    public String mostrarProducto(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("producto", servicio.obtenerProductoPorId(id));
        return "articulo";

    }

    @GetMapping("/busqueda/categoria/hombre")
    public String busquedaPorCategoríaHombre() {
        return "inicioCategoriaHombre";
    }

    @GetMapping("/busqueda/categoria/mujer")
    public String busquedaPorCategoríaMujer() {
        return "inicioCategoriaMujer";
    }

    @GetMapping("/busqueda/categoria/nino")
    public String busquedaPorCategoríaNino() {
        return "inicioCategoriaNino";
    }


    @GetMapping("/busqueda/categoria/hombre/porTipo/polera")
    public String busquedaPorCategoriaHYTipoPolera(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Polera");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/hombre/porTipo/poleron")
    public String busquedaPorCategoriaHYTipoPoleron(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Poleron");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/hombre/porTipo/camisa")
    public String busquedaPorCategoriaHYTipoCamisa(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Camisa");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/hombre/porTipo/pantalon")
    public String busquedaPorCategoriaHYTipoPantalon(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Pantalon");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/hombre/porTipo/zapato")
    public String busquedaPorCategoriaHYTipoZapato(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Zapato");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/hombre/porTipo/chaqueta")
    public String busquedaPorCategoriaHYTipoChaqueta(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Hombre","Chaqueta");
        model.addAttribute("producto",productos);
        return "busqueda";
    }



    @GetMapping("/busqueda/categoria/mujer/porTipo/polera")
    public String busquedaPorCategoriaMYTipoPolera(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Polera");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/mujer/porTipo/poleron")
    public String busquedaPorCategoriaMYTipoPoleron(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Poleron");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/mujer/porTipo/camisa")
    public String busquedaPorCategoriaMYTipoCamisa(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Camisa");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/mujer/porTipo/pantalon")
    public String busquedaPorCategoriaMYTipoPantalon(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Pantalon");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/mujer/porTipo/zapato")
    public String busquedaPorCategoriaMYTipoZapato(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Zapato");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/mujer/porTipo/chaqueta")
    public String busquedaPorCategoriaMYTipoChaqueta(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Chaqueta");
        model.addAttribute("producto",productos);
        return "busqueda";
    }
    @GetMapping("/busqueda/categoria/mujer/porTipo/blusa")
    public String busquedaPorCategoriaMYTipoBlusa(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Blusa");
        model.addAttribute("producto",productos);
        return "busqueda";
    }
    @GetMapping("/busqueda/categoria/mujer/porTipo/falda")
    public String busquedaPorCategoriaMYTipoFalda(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Mujer","Falda");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/nino/porTipo/polera")
    public String busquedaPorCategoriaNYTipoPolera(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Polera");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/nino/porTipo/poleron")
    public String busquedaPorCategoriaNYTipoPoleron(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Poleron");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/nino/porTipo/camisa")
    public String busquedaPorCategoriaNYTipoCamisa(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Camisa");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/nino/porTipo/pantalon")
    public String busquedaPorCategoriaNYTipoPantalon(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Pantalon");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/nino/porTipo/zapato")
    public String busquedaPorCategoriaNYTipoZapato(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Zapato");
        model.addAttribute("producto",productos);
        return "busqueda";
    }

    @GetMapping("/busqueda/categoria/nino/porTipo/chaqueta")
    public String busquedaPorCategoriaNYTipoChaqueta(Model model){
        List<Producto> productos= servicio.obteneProductoPorCategoriaYTipoProducto(1,"Niño","Chaqueta");
        model.addAttribute("producto",productos);
        return "busqueda";
    }






}
