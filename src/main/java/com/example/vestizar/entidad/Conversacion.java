package com.example.vestizar.entidad;

import javax.persistence.*;

@Entity
@Table(name = "conversaciones")
public class Conversacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConversacion;



    public Conversacion(){}

    public Conversacion(Long idConversacion) {
        this.idConversacion = idConversacion;
    }

    public Long getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Long idConversacion) {
        this.idConversacion = idConversacion;
    }
}
