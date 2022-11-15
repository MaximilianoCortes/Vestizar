package com.example.vestizar.aplicacion.Enums;

public enum tipoEstado {

    NUEVO("Nuevo"),
    SEMINUEVO("Semi-Nuevo"),
    USADO("Usado");
    private final String nombre;

    tipoEstado(String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
