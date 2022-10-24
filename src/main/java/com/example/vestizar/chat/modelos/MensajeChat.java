package com.example.vestizar.chat.modelos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class MensajeChat {
    @Id
    private String id_mensaje;
    private String id_conversacion;
    private String id_remitente;
    private String id_receptor;
    private String nombre_remitente;
    private String contenido;
    private Date fecha;
    private EstadoMensaje estado;
}
