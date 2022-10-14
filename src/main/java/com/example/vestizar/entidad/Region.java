package com.example.vestizar.entidad;

import javax.persistence.*;

@Entity
@Table(name = "regiones")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegion;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    public Region(){
    }

    public Region(Long idRegion, String nombre) {
        this.idRegion = idRegion;
        this.nombre = nombre;
    }


    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


