package com.example.vestizar.aplicacion.Enums;

public enum tipoCiudad {

    TEMUCO("Temuco"),
    VILLARRICA("Villarrica"),
    SANTIAGO("Santiago"),
    VALDIVIA("Valdivia");
    private final String nombre;

    tipoCiudad(String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
