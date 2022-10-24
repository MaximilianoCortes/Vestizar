package com.example.vestizar.chat.modelos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificacionChat {
    private String id;
    private String id_remitente;
    private String nombre_remitente;
}
