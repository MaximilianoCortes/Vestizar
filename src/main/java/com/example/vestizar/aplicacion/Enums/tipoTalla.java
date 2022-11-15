package com.example.vestizar.aplicacion.Enums;

public enum tipoTalla {

    S("S"),
    M("M"),
    XS("XS"),
    XL("XL"),
    XXL("XXL");

    private final String nombre;

    tipoTalla (String nombre){
        this.nombre=nombre;
    }

    public String getNombre(){
        return nombre;
    }




}
