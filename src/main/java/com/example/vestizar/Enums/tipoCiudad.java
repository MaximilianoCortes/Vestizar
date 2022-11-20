package com.example.vestizar.Enums;

public enum tipoCiudad {

    TEMUCO("Temuco"),
    VILLARRICA("Villarrica"),
    SANTIAGO("Santiago"),
    VALDIVIA("Valdivia"),
    COPIAPO("Copiapó");
    private final String nombre;

    tipoCiudad(String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
