package com.example.vestizar.servicio;

import com.example.vestizar.entidad.Ciudad;
import com.example.vestizar.repositorio.CiudadRepositorio;
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
