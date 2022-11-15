package com.example.vestizar.aplicacion.repositorio;



import com.example.vestizar.aplicacion.entidad.MensajeChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * interface Mensaje repositorio.
 */
@Repository
public interface MensajeRepositorio
        extends JpaRepository<MensajeChat, Long> {

    /**
     * cuenta el numero de filas almacenadas en la base de datos con mensajes que tengna la id del remitente y del receptor
     *
     * @param idRemitente id remitente
     * @param IdReceptor  id receptor
     * @return numero de mensajes encontrados
     */
    long countByRemitenteAndReceptor(
            Long idRemitente, Long IdReceptor);

    /**
     * busca todos los mensajes en la base de datos que coincidan con la id de la conversacion
     *
     * @param idConversacion id de la conversacion
     * @return lista de los mensajes encontrados
     */
    List<MensajeChat> findByConversacion(String idConversacion);
}
