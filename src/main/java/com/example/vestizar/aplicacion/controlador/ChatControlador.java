package com.example.vestizar.aplicacion.controlador;


import com.example.vestizar.aplicacion.entidad.MensajeChat;
import com.example.vestizar.aplicacion.entidad.NotificacionChat;
import com.example.vestizar.aplicacion.entidad.Producto;
import com.example.vestizar.aplicacion.servicio.ConversacionServicio;
import com.example.vestizar.aplicacion.servicio.MensajeChatServicio;
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
     * cuenta los mensajes que hay entre el remitente y el receptor.
     *
     * @param idRemitente id del remitente
     * @param idReceptor  id del receptor
     * @return mappea el numero de mensajes encontrados
     */
    @GetMapping("/mensajes/{idRemitente}/{idReceptor}/count")
    public ResponseEntity<Long> contarNuevosMensajes(
            @PathVariable Long idRemitente,
            @PathVariable Long idReceptor) {

        return ResponseEntity
                .ok(mensajeChatServicio.contarNuevosMensajes(idRemitente, idReceptor));
    }


    /**
     * Encuentra todos los mensajes entre el remitente y el receptor
     *
     * @param idRemitente id del remitente
     * @param idReceptor  id del receptor
     * @return los mensajes encontrados
     */
    @GetMapping("/mensajes/{idRemitente}/{idReceptor}")
    public ResponseEntity<?> encontrarMensajesChat(@PathVariable Long idRemitente,
                                                   @PathVariable Long idReceptor) {
        return ResponseEntity
                .ok(mensajeChatServicio.encontrarMensajesChat(idRemitente, idReceptor));
    }

    /**
     * busca un mensaje por su id generada
     *
     * @param id id del mensaje
     * @return el mensaje encontrado con la misma id
     */
    @GetMapping("/mensajes/{id}")
    public ResponseEntity<?> encontrarMensaje ( @PathVariable Long id) {
        return ResponseEntity
                .ok(mensajeChatServicio.encontrarPorId(id));
    }
}
