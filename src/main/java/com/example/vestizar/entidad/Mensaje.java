package com.example.vestizar.entidad;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mensajes")
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensaje;

    @Column(name = "fecha_enviado", nullable = false)
    private Date fechaEnviado;

    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_remitente")
    private Usuario remitente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conversacion")
    private Conversacion conversacion;


    public Mensaje() {

    }

    public Mensaje(Long idMensaje, Date fechaEnviado, String mensaje, Usuario remitente, Conversacion conversacion) {
        this.idMensaje = idMensaje;
        this.fechaEnviado = fechaEnviado;
        this.mensaje = mensaje;
        this.remitente = remitente;
        this.conversacion = conversacion;
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

    public Usuario getRemitente() {
        return remitente;
    }

    public void setRemitente(Usuario usuario) {
        this.remitente = usuario;
    }

    public Conversacion getConversacion() {
        return conversacion;
    }

    public void setConversacion(Conversacion conversacion) {
        this.conversacion = conversacion;
    }
}
