package com.example.vestizar.aplicacion.entidad;


public class NotificacionChat {
    private Long id;
    private Long id_remitente;
    private String nombre_remitente;

    public NotificacionChat() {
    }

    public NotificacionChat(Long id, Long id_remitente, String nombre_remitente) {
        this.id = id;
        this.id_remitente = id_remitente;
        this.nombre_remitente = nombre_remitente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_remitente() {
        return id_remitente;
    }

    public void setId_remitente(Long id_remitente) {
        this.id_remitente = id_remitente;
    }

    public String getNombre_remitente() {
        return nombre_remitente;
    }

    public void setNombre_remitente(String nombre_remitente) {
        this.nombre_remitente = nombre_remitente;
    }
}
