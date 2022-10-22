package com.example.vestizar.Enums;

public enum tipoCiudad {

    TEMUCO("Temuco"),
    VILLARRICA("Villarrica"),
    SANTIAGO("Santiago");

    private final String nombre;

    tipoCiudad(String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
