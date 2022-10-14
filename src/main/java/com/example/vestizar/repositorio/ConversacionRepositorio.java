package com.example.vestizar.repositorio;

import com.example.vestizar.entidad.Conversacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversacionRepositorio extends JpaRepository<Conversacion,Long> {
}
