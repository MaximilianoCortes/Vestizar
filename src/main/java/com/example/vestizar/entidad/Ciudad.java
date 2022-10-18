package com.example.vestizar.entidad;

import javax.persistence.*;

@Entity
@Table(name = "ciudades")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCiudad;

    @Column(name = "nombre",nullable = false)
    private String nombre;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_region")
    private Region region;


    public Ciudad() {
    }

    public Ciudad(Long idCiudad, String nombre, Region region) {
        this.idCiudad = idCiudad;
        this.nombre = nombre;
        this.region = region;
    }

    public Long getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
