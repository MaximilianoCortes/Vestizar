package com.example.vestizar.Enums;

public enum tipoTalla {

    S("S"),
    M("M"),
    XS("XS"),
    XL("XL");

    private final String nombre;

    tipoTalla (String nombre){
        this.nombre=nombre;
    }

    public String getNombre(){
        return nombre;
    }




}
