package com.example.vestizar.aplicacion.servicio;

import com.example.vestizar.aplicacion.entidad.Usuario;
import com.example.vestizar.aplicacion.servicio.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    UsuarioRepositorio repositorio;


    public void crearNuevoUsuario(Usuario usuario){
        repositorio.save(usuario);
    }




}
