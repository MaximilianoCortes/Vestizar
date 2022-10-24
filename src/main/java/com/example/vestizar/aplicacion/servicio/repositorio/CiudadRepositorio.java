package com.example.vestizar.aplicacion.servicio.repositorio;

import com.example.vestizar.aplicacion.entidad.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepositorio extends JpaRepository<Ciudad,Long> {
}
