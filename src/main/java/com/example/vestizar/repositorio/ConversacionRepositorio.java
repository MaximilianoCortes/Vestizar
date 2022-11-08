package com.example.vestizar.repositorio;

import com.example.vestizar.entidad.Conversacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Conversacion repositorio.
 */
@Repository
public interface ConversacionRepositorio extends JpaRepository<Conversacion, String> {
    /**
     * Find by remitente and receptor optional.
     *
     * @param idRemitente the id remitente
     * @param recipientId the recipient id
     * @return the optional
     */
    Optional<Conversacion> findByRemitenteAndReceptor(Long idRemitente, Long recipientId);
}