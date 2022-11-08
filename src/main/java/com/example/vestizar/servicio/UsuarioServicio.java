package com.example.vestizar.servicio;

import com.example.vestizar.entidad.Usuario;
import com.example.vestizar.repositorio.UsuarioRepositorio;
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
