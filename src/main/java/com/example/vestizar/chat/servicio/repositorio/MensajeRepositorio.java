package com.example.vestizar.chat.servicio.repositorio;



import com.example.vestizar.chat.modelos.EstadoMensaje;
import com.example.vestizar.chat.modelos.MensajeChat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MensajeRepositorio
        extends MongoRepository<MensajeChat, String> {

    long countById_remitenteAndId_receptorAndEstado(
            String idRemitente, String IdReceptor, EstadoMensaje estado);

    List<MensajeChat> findById_conversacion(String idConversacion);
}
