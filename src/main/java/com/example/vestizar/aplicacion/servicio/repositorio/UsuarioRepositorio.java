package com.example.vestizar.aplicacion.servicio.repositorio;

import com.example.vestizar.aplicacion.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Usuario
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
}
