package com.example.vestizar.controlador;


import com.example.vestizar.entidad.MensajeChat;
import com.example.vestizar.entidad.NotificacionChat;
import com.example.vestizar.servicio.ConversacionServicio;
import com.example.vestizar.servicio.MensajeChatServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ChatControlador {

    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private MensajeChatServicio mensajeChatServicio;
    @Autowired private ConversacionServicio conversacionServicio;

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

    @GetMapping("/mensajes/{idRemitente}/{idReceptor}/count")
    public ResponseEntity<Long> contarNuevosMensajes(
            @PathVariable Long idRemitente,
            @PathVariable Long idReceptor) {

        return ResponseEntity
                .ok(mensajeChatServicio.contarNuevosMensajes(idRemitente, idReceptor));
    }

    @GetMapping("/mensajes/{idRemitente}/{idReceptor}")
    public ResponseEntity<?> encontrarMensajesChat(@PathVariable Long idRemitente,
                                                   @PathVariable Long idReceptor) {
        return ResponseEntity
                .ok(mensajeChatServicio.encontrarMensajesChat(idRemitente, idReceptor));
    }

    @GetMapping("/mensajes/{id}")
    public ResponseEntity<?> encontrarMensaje ( @PathVariable Long id) {
        return ResponseEntity
                .ok(mensajeChatServicio.encontrarPorId(id));
    }
}
