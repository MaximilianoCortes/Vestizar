package com.example.vestizar.aplicacion.servicio.repositorio;

import com.example.vestizar.aplicacion.entidad.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepositorio extends JpaRepository<Region,Long> {
}
