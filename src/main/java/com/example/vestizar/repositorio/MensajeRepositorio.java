package com.example.vestizar.repositorio;

import com.example.vestizar.entidad.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepositorio extends JpaRepository<Mensaje,Long> {
}
