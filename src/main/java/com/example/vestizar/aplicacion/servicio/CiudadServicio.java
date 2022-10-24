package com.example.vestizar.aplicacion.servicio;

import com.example.vestizar.aplicacion.entidad.Ciudad;
import com.example.vestizar.aplicacion.servicio.repositorio.CiudadRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadServicio {

    @Autowired
    private CiudadRepositorio repositorio;


    public  List<Ciudad> listarCiudad(){
        return repositorio.findAll();
    }


}
