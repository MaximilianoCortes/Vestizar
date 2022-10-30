package com.example.vestizar.chat.servicio;

import com.example.vestizar.chat.modelos.Conversacion;
import com.example.vestizar.chat.servicio.repositorio.ConversacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConversacionServicio {

    @Autowired private ConversacionRepositorio conversacionRepositorio;

    public Optional<String> getIdConversacion(
            String idRemitente, String idReceptor, boolean crearSiNoExiste) {

        return conversacionRepositorio
                .findById_RemitenteAndId_Receptor(idRemitente, idReceptor)
                .map(Conversacion::getId_Conversacion)
                .or(() -> {
                    if(!crearSiNoExiste) {
                        return  Optional.empty();
                    }
                    var IdConversacion =
                            String.format("%s_%s", idRemitente, idReceptor);

                    Conversacion emisorDestinatario = Conversacion
                            .builder()
                            .id_Conversacion(IdConversacion)
                            .id_Remitente(idRemitente)
                            .id_Receptor(idReceptor)
                            .build();

                    Conversacion destinatarioEmisor = Conversacion
                            .builder()
                            .id_Conversacion(IdConversacion)
                            .id_Remitente(idReceptor)
                            .id_Receptor(idRemitente)
                            .build();
                    conversacionRepositorio.save(emisorDestinatario);
                    conversacionRepositorio.save(destinatarioEmisor);

                    return Optional.of(IdConversacion);
                });
    }
}
