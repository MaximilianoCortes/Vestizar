package com.example.vestizar.aplicacion.entidad;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mensajes")
public class MensajeChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje", nullable = false)
    private Long id_mensaje;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "contenido", nullable = false)
    private String contenido;

    @Column(name = "remitente", nullable = false)
    private Long remitente;

    @Column(name = "nombre_remitente", nullable = false)
    private String nombre_remitente;

    @Column(name = "receptor", nullable = false)
    private Long receptor;


    @Column(name = "conversacion", nullable = false)
    private String conversacion;


    public MensajeChat() {

    }

    public MensajeChat(Long id_mensaje, Date fecha, String contenido, Long id_remitente, String nombre_remitente, Long id_receptor, String id_conversacion) {
        this.id_mensaje = id_mensaje;
        this.fecha = fecha;
        this.contenido = contenido;
        this.remitente = id_remitente;
        this.nombre_remitente = nombre_remitente;
        this.receptor = id_receptor;
        this.conversacion = id_conversacion;
    }

    public Long getId_mensaje() {
        return id_mensaje;
    }

    public void setId_mensaje(Long id_mensaje) {
        this.id_mensaje = id_mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Long getRemitente() {
        return remitente;
    }

    public void setRemitente(Long id_remitente) {
        this.remitente = id_remitente;
    }

    public String getNombre_remitente() {
        return nombre_remitente;
    }

    public void setNombre_remitente(String nombre_remitente) {
        this.nombre_remitente = nombre_remitente;
    }

    public Long getReceptor() {
        return receptor;
    }

    public void setReceptor(Long id_receptor) {
        this.receptor = id_receptor;
    }

    public String getConversacion() {
        return conversacion;
    }

    public void setConversacion(String id_conversacion) {
        this.conversacion = id_conversacion;
    }
}
