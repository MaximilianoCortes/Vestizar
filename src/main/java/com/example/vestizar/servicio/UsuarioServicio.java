package com.example.vestizar.servicio;

import com.example.vestizar.entidad.Usuario;
import com.example.vestizar.repositorio.UsuarioRepositorio;
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
