package com.example.vestizar.repositorio;



import com.example.vestizar.entidad.MensajeChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Mensaje repositorio.
 */
@Repository
public interface MensajeRepositorio
        extends JpaRepository<MensajeChat, Long> {

    /**
     * Count by remitente and receptor long.
     *
     * @param idRemitente the id remitente
     * @param IdReceptor  the id receptor
     * @return the long
     */
    long countByRemitenteAndReceptor(
            Long idRemitente, Long IdReceptor);

    /**
     * Find by conversacion list.
     *
     * @param idConversacion the id conversacion
     * @return the list
     */
    List<MensajeChat> findByConversacion(String idConversacion);
}
