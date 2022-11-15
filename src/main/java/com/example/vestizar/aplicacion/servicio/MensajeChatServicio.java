package com.example.vestizar.aplicacion.servicio;


import com.example.vestizar.ResourceNotFoundException;
import com.example.vestizar.aplicacion.entidad.MensajeChat;
import com.example.vestizar.aplicacion.repositorio.MensajeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * servicio mensaje chat
 */
@Service
public class MensajeChatServicio {
    @Autowired private MensajeRepositorio mensajeRepositorio;
    @Autowired private ConversacionServicio conversacionServicio;


    /**
     * guarda el mensaje enviado en la base de datos mediante el repositorio
     *
     * @param mensajeChat objeto mensaje
     * @return el mensaje guardado
     */
    public MensajeChat save(MensajeChat mensajeChat) {
        mensajeRepositorio.save(mensajeChat);
        return mensajeChat;
    }

    /**
     * cuenta los mensajes que coincidan con las id entregadas y devuelve la cantidad encontrada
     *
     * @param idRemitente id remitente
     * @param IdReceptor  id receptor
     * @return cantidad de mensajes encontrados
     */
    public long contarNuevosMensajes(Long idRemitente, Long IdReceptor) {
        return mensajeRepositorio.countByRemitenteAndReceptor(
                idRemitente, IdReceptor);
    }

    /**
     * encuentra en la base de datos los mensajes que coincidan con las id entregadas y devuelve una lista con estos mensajes
     *
     * @param idRemitente id remitente
     * @param idReceptor  id receptor
     * @return lista con los mensajes encontrados
     */
    public List<MensajeChat> encontrarMensajesChat(Long idRemitente, Long idReceptor) {
        Optional<String> idConversacion = conversacionServicio.getIdConversacion(idRemitente, idReceptor, false);

        var mensajes =
                idConversacion.map(cId -> mensajeRepositorio.findByConversacion(cId)).orElse(new ArrayList<>());

        return mensajes;
    }

    /**
     * Encuentra en la base de datos los mensajes que coincidan con la id entregada y los devuelve..
     *
     * @param id id del mensaje
     * @return el mensaje encontrado
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
