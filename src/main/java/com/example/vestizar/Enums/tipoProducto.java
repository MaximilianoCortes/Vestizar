package com.example.vestizar.Enums;

public enum tipoProducto {
    POLERA("Polera"),
    CAMISA("Camisa"),
    POLERON("Poleron"),
    PANTALON("Pantalon");

    private final String nombre;


    tipoProducto(String nombre){
        this.nombre=nombre;
    }

    public String getNombre(){
        return nombre;
    }


}
