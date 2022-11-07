package com.example.vestizar.servicio;


import com.example.vestizar.ResourceNotFoundException;
import com.example.vestizar.entidad.MensajeChat;
import com.example.vestizar.repositorio.MensajeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MensajeChatServicio {
    @Autowired private MensajeRepositorio mensajeRepositorio;
    @Autowired private ConversacionServicio conversacionServicio;

    public MensajeChat save(MensajeChat mensajeChat) {
        mensajeRepositorio.save(mensajeChat);
        return mensajeChat;
    }

    public long contarNuevosMensajes(Long idRemitente, Long IdReceptor) {
        return mensajeRepositorio.countByRemitenteAndReceptor(
                idRemitente, IdReceptor);
    }

    public List<MensajeChat> encontrarMensajesChat(Long idRemitente, Long idReceptor) {
        Optional<String> idConversacion = conversacionServicio.getIdConversacion(idRemitente, idReceptor, false);

        var mensajes =
                idConversacion.map(cId -> mensajeRepositorio.findByConversacion(cId)).orElse(new ArrayList<>());

        return mensajes;
    }

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
