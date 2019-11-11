package com.example.pruebaandroid.model;

public class Medicamento {

    public Medicamento() {

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public String getConcentracion() {
        return Concentracion;
    }

    public void setConcentracion(String concentracion) {
        Concentracion = concentracion;
    }

    private String Nombre;
    private String Cantidad;
    private String Concentracion;

}
