package com.example.vestizar.servicio;


import com.example.vestizar.ResourceNotFoundException;
import com.example.vestizar.entidad.MensajeChat;
import com.example.vestizar.repositorio.MensajeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Mensaje chat servicio.
 */
@Service
public class MensajeChatServicio {
    @Autowired private MensajeRepositorio mensajeRepositorio;
    @Autowired private ConversacionServicio conversacionServicio;


    /**
     * Save mensaje chat.
     *
     * @param mensajeChat the mensaje chat
     * @return the mensaje chat
     */
    public MensajeChat save(MensajeChat mensajeChat) {
        mensajeRepositorio.save(mensajeChat);
        return mensajeChat;
    }

    /**
     * Contar nuevos mensajes long.
     *
     * @param idRemitente the id remitente
     * @param IdReceptor  the id receptor
     * @return the long
     */
    public long contarNuevosMensajes(Long idRemitente, Long IdReceptor) {
        return mensajeRepositorio.countByRemitenteAndReceptor(
                idRemitente, IdReceptor);
    }

    /**
     * Encontrar mensajes chat list.
     *
     * @param idRemitente the id remitente
     * @param idReceptor  the id receptor
     * @return the list
     */
    public List<MensajeChat> encontrarMensajesChat(Long idRemitente, Long idReceptor) {
        Optional<String> idConversacion = conversacionServicio.getIdConversacion(idRemitente, idReceptor, false);

        var mensajes =
                idConversacion.map(cId -> mensajeRepositorio.findByConversacion(cId)).orElse(new ArrayList<>());

        return mensajes;
    }

    /**
     * Encontrar por id mensaje chat.
     *
     * @param id the id
     * @return the mensaje chat
     */
    public MensajeChat encontrarPorId(Long id) {
        return mensajeRepositorio
                .findById(id)
                .map(chatMessage -> {
                    return mensajeRepositorio.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("se no puede encontrar el mensaje (" + id + ")"));
    }

}
