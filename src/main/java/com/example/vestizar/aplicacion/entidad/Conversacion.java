package com.example.vestizar.aplicacion.entidad;



import javax.persistence.*;



@Entity
@Table(name = "conversacion")
public class Conversacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "nombre_conversacion", nullable = false)
    private String nombre_Conversacion;
    @Column(name = "remitente", nullable = false)
    private Long remitente;
    @Column(name = "receptor", nullable = false)
    private Long receptor;

    public Conversacion() {
    }

    public Conversacion(Long id, String id_Conversacion, Long id_Remitente, Long id_Receptor) {
        this.id = id;
        this.nombre_Conversacion = id_Conversacion;
        this.remitente = id_Remitente;
        this.receptor = id_Receptor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre_Conversacion() {
        return nombre_Conversacion;
    }

    public void setNombre_Conversacion(String id_Conversacion) {
        this.nombre_Conversacion = id_Conversacion;
    }

    public Long getRemitente() {
        return remitente;
    }

    public void setRemitente(Long id_Remitente) {
        this.remitente = id_Remitente;
    }

    public Long getReceptor() {
        return receptor;
    }

    public void setReceptor(Long id_Receptor) {
        this.receptor = id_Receptor;
    }
}