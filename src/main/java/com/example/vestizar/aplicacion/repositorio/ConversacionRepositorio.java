package com.example.vestizar.aplicacion.repositorio;

import com.example.vestizar.aplicacion.entidad.Conversacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * interface Conversacion repositorio.
 */
@Repository
public interface ConversacionRepositorio extends JpaRepository<Conversacion, String> {
    /**
     * query a la base de datos para encontrar una conversacion por las id del remitente y del receptor
     *
     * @param idRemitente id remitente
     * @param idReceptor  id del receptor
     * @return la conversacion
     */
    Optional<Conversacion> findByRemitenteAndReceptor(Long idRemitente,Long idReceptor);
}