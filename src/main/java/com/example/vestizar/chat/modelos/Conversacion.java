package com.example.vestizar.chat.modelos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Conversacion {
    @Id
    private String id;
    private String id_Conversacion;
    private String id_Remitente;
    private String id_Receptor;
}