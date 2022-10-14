package com.example.vestizar.repositorio;

import com.example.vestizar.entidad.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepositorio extends JpaRepository<Region,Long> {
}
