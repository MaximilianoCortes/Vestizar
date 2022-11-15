package com.example.vestizar.chat.controlador;


import com.example.vestizar.chat.modelos.MensajeChat;
import com.example.vestizar.chat.modelos.NotificacionChat;
import com.example.vestizar.chat.servicio.ConversacionServicio;
import com.example.vestizar.chat.servicio.MensajeChatServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatControlador {

    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private MensajeChatServicio mensajeChatServicio;
    @Autowired private ConversacionServicio conversacionServicio;

    @MessageMapping("/chat")
    public void processMessage(@Payload MensajeChat mensajeChat) {
        var idConversacion = conversacionServicio
                .getIdConversacion(mensajeChat.getId_remitente(), mensajeChat.getId_receptor(), true);
        mensajeChat.setId_conversacion(idConversacion.get());

        MensajeChat almacenado = mensajeChatServicio.save(mensajeChat);
        messagingTemplate.convertAndSendToUser(
                mensajeChat.getId_receptor(),"/queue/messages",
                new NotificacionChat(
                        almacenado.getId_mensaje(),
                        almacenado.getId_remitente(),
                        almacenado.getNombre_remitente()));
    }

    @GetMapping("/messages/{idRemitente}/{idReceptor}/count")
    public ResponseEntity<Long> contarNuevosMensajes(
            @PathVariable String idRemitente,
            @PathVariable String idReceptor) {

        return ResponseEntity
                .ok(mensajeChatServicio.contarNuevosMensajes(idRemitente, idReceptor));
    }

    @GetMapping("/messages/{idRemitente}/{idReceptor}")
    public ResponseEntity<?> encontrarMensajesChat(@PathVariable String idRemitente,
                                                   @PathVariable String idReceptor) {
        return ResponseEntity
                .ok(mensajeChatServicio.encontrarMensajesChat(idRemitente, idReceptor));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> encontrarMensaje ( @PathVariable String id) {
        return ResponseEntity
                .ok(mensajeChatServicio.encontrarPorId(id));
    }
}
