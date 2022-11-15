package com.example.vestizar.servicio;

import com.example.vestizar.entidad.Conversacion;
import com.example.vestizar.repositorio.ConversacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * servicio de conversacion
 */
@Service
public class ConversacionServicio {

    @Autowired private ConversacionRepositorio conversacionRepositorio;

    /**
     * genera una id de conversacion en caso de que no exista, juntando la id del remitente y del receptor
     *
     * @param idRemitente     id del remitente
     * @param idReceptor      id del receptor
     * @param crearSiNoExiste booleano, indica si es necesario crear id de conversacion
     * @return id de la conversacion
     */
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
