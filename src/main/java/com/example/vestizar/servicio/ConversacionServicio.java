package com.example.vestizar.servicio;

import com.example.vestizar.entidad.Conversacion;
import com.example.vestizar.repositorio.ConversacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConversacionServicio {

    @Autowired private ConversacionRepositorio conversacionRepositorio;

    public Optional<String> getIdConversacion(
            Long idRemitente, Long idReceptor, boolean crearSiNoExiste) {

        return conversacionRepositorio
                .findByRemitenteAndReceptor(idRemitente, idReceptor)
                .map(Conversacion::getNombre_Conversacion)
                .or(() -> {
                    if(!crearSiNoExiste) {
                        return  Optional.empty();
                    }

                    String IdConversacion =
                            String.format("%s_%s", idRemitente, idReceptor);

                    Conversacion emisorDestinatario = new Conversacion();
                    emisorDestinatario.setNombre_Conversacion(IdConversacion);
                    emisorDestinatario.setReceptor(idReceptor);
                    emisorDestinatario.setReceptor(idRemitente);

                    Conversacion destinatarioEmisor= new Conversacion();
                    emisorDestinatario.setNombre_Conversacion(IdConversacion);
                    emisorDestinatario.setReceptor(idRemitente);
                    emisorDestinatario.setReceptor(idReceptor);

                    conversacionRepositorio.save(emisorDestinatario);
                    conversacionRepositorio.save(destinatarioEmisor);

                    return Optional.of(IdConversacion);
                });
    }
}
