package com.example.vestizar.controlador;


import com.example.vestizar.entidad.MensajeChat;
import com.example.vestizar.entidad.NotificacionChat;
import com.example.vestizar.entidad.Producto;
import com.example.vestizar.servicio.ConversacionServicio;
import com.example.vestizar.servicio.MensajeChatServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

/**
 * clase Controlador del chat
 */
@Controller
public class ChatControlador {

    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private MensajeChatServicio mensajeChatServicio;
    @Autowired private ConversacionServicio conversacionServicio;

    @GetMapping("/inicioChat")
    public String inicioChat() {
        return "contactos";
    }

    /**
     * obtiene la id de la conversacion que es el equivalente a las id de los 2 usuarios en formato xxyy siendo x la id del remitente e y la del receptor
     * le asigna la id al mensaje enviado y lo almacena en la base de datos, posteriormente utiliza una funcion de la clase messagin template para enviar
     * el mensaje al usuario y generar una notificacion con los datos del objeto.
     *
     * @param mensajeChat objeto mensaje, tiene los datos de quien lo envio, a que hora, a quien, en cual conversacion y su contenido
     */
    @MessageMapping("/chat")
    public void procesarMensajes(@Payload MensajeChat mensajeChat) {
        Optional<String> idConversacion = conversacionServicio
                .getIdConversacion(mensajeChat.getRemitente(), mensajeChat.getReceptor(), true);
        mensajeChat.setConversacion(idConversacion.get());

        MensajeChat almacenado = mensajeChatServicio.save(mensajeChat);
        messagingTemplate.convertAndSendToUser(
                mensajeChat.getReceptor().toString(),"/queue/messages",
                new NotificacionChat(
                        almacenado.getId_mensaje(),
                        almacenado.getRemitente(),
                        almacenado.getNombre_remitente()));
    }

    /**
     * Contar nuevos mensajes response entity.
     *
     * @param idRemitente the id remitente
     * @param idReceptor  the id receptor
     * @return the response entity
     */
    @GetMapping("/mensajes/{idRemitente}/{idReceptor}/count")
    public ResponseEntity<Long> contarNuevosMensajes(
            @PathVariable Long idRemitente,
            @PathVariable Long idReceptor) {

        return ResponseEntity
                .ok(mensajeChatServicio.contarNuevosMensajes(idRemitente, idReceptor));
    }


    /**
     * Encontrar mensajes chat response entity.
     *
     * @param idRemitente the id remitente
     * @param idReceptor  the id receptor
     * @return the response entity
     */
    @GetMapping("/mensajes/{idRemitente}/{idReceptor}")
    public ResponseEntity<?> encontrarMensajesChat(@PathVariable Long idRemitente,
                                                   @PathVariable Long idReceptor) {
        return ResponseEntity
                .ok(mensajeChatServicio.encontrarMensajesChat(idRemitente, idReceptor));
    }

    /**
     * Encontrar mensaje response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/mensajes/{id}")
    public ResponseEntity<?> encontrarMensaje ( @PathVariable Long id) {
        return ResponseEntity
                .ok(mensajeChatServicio.encontrarPorId(id));
    }
}
