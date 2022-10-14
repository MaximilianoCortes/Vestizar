package com.example.vestizar.entidad;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mensajes")
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensaje;

    @Column(name ="fecha_enviado",nullable = false)
    private Date fechaEnviado;

    @Column(name ="mensaje",nullable = false)
    private String mensaje;


    public Mensaje(){

    }

    public Mensaje(Long idMensaje, Date fechaEnviado, String mensaje) {
        this.idMensaje = idMensaje;
        this.fechaEnviado = fechaEnviado;
        this.mensaje = mensaje;
    }

    public Long getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Long idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Date getFechaEnviado() {
        return fechaEnviado;
    }

    public void setFechaEnviado(Date fechaEnviado) {
        this.fechaEnviado = fechaEnviado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
