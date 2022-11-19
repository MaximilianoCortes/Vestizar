package com.example.vestizar.repositorio;

import com.example.vestizar.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Usuario
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
    Usuario findByEmail(String email);
}
