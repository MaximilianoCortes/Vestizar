package com.example.vestizar.repositorio;

import com.example.vestizar.entidad.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepositorio extends JpaRepository<Ciudad,Long> {
}
