package com.example.vestizar.chat.servicio;


import com.example.vestizar.chat.ResourceNotFoundException;
import com.example.vestizar.chat.modelos.EstadoMensaje;
import com.example.vestizar.chat.modelos.MensajeChat;
import com.example.vestizar.chat.servicio.repositorio.MensajeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MensajeChatServicio {
    @Autowired private MensajeRepositorio mensajeRepositorio;
    @Autowired private ConversacionServicio conversacionServicio;
    @Autowired private MongoOperations mongoOperations;

    public MensajeChat save(MensajeChat mensajeChat) {
        mensajeChat.setEstado(EstadoMensaje.RECIBIDO);
        mensajeRepositorio.save(mensajeChat);
        return mensajeChat;
    }

    public long contarNuevosMensajes(String idRemitente, String IdReceptor) {
        return mensajeRepositorio.contarPorIdRemitenteReceptorYEstado(
                idRemitente, IdReceptor, EstadoMensaje.RECIBIDO);
    }

    public List<MensajeChat> encontrarMensajesChat(String idRemitente, String idReceptor) {
        var idConversacion = conversacionServicio.getIdConversacion(idRemitente, idReceptor, false);

        var mensajes =
                idConversacion.map(cId -> mensajeRepositorio.encontrarPorIdConversacion(cId)).orElse(new ArrayList<>());

        if(mensajes.size() > 0) {
            ActualizarEstado(idRemitente, idReceptor, EstadoMensaje.ENVIADO);
        }

        return mensajes;
    }

    public MensajeChat encontrarPorId(String id) {
        return mensajeRepositorio
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setEstado(EstadoMensaje.ENVIADO);
                    return mensajeRepositorio.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("can't find message (" + id + ")"));
    }

    public void ActualizarEstado(String idRemitente, String idReceptor, EstadoMensaje estado) {
        Query query = new Query(
                Criteria
                        .where("idRemitente").is(idRemitente)
                        .and("idReceptor").is(idReceptor));
        Update update = Update.update("estado", estado);
        mongoOperations.updateMulti(query, update, MensajeChat.class);
    }
}
