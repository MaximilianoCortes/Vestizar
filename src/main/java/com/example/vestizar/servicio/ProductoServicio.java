package com.example.vestizar.servicio;

import com.example.vestizar.entidad.Producto;
import com.example.vestizar.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServicio {


    @Autowired
    ProductoRepositorio repositorio;


    public void crearNuevoProducto(Producto producto){
        repositorio.save(producto);

    }



}
