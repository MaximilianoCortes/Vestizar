package com.example.vestizar.Enums;

public enum tipoCategoria {

    HOMBRE("Hombre"),
    MUJER("Mujer"),
    NIÑO("Niños");
    private final String nombre;

    tipoCategoria(String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
