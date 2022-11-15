package com.example.vestizar.aplicacion.servicio;

import com.example.vestizar.aplicacion.entidad.Usuario;
import com.example.vestizar.aplicacion.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Usuario servicio.
 */
@Service
public class UsuarioServicio {

    /**
     * The Repositorio.
     */
    @Autowired
    UsuarioRepositorio repositorio;


    /**
     * Crear nuevo usuario.
     *
     * @param usuario the usuario
     */
    public void crearNuevoUsuario(Usuario usuario){
        repositorio.save(usuario);
    }




}
