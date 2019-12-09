package com.example.pruebaandroid.model;

public class Alerta {
    private String Dosis;
    private String Duracion;
    private String FechaInicio;
    private String Frecuencia;
    private String Intervalo;
    private String Nombre;
    private String Paciente;

    public Alerta() {
    }

    public Alerta(String dosis, String duracion, String fechaInicio, String frecuencia, String intervalo, String nombre, String paciente) {
        Dosis = dosis;
        Duracion = duracion;
        FechaInicio = fechaInicio;
        Frecuencia = frecuencia;
        Intervalo = intervalo;
        Nombre = nombre;
        Paciente = paciente;
    }

    public String getDosis() {
        return Dosis;
    }

    public void setDosis(String dosis) {
        Dosis = dosis;
    }

    public String getDuracion() {
        return Duracion;
    }

    public void setDuracion(String duracion) {
        Duracion = duracion;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public String getFrecuencia() {
        return Frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        Frecuencia = frecuencia;
    }

    public String getIntervalo() {
        return Intervalo;
    }

    public void setIntervalo(String intervalo) {
        Intervalo = intervalo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPaciente() {
        return Paciente;
    }

    public void setPaciente(String paciente) {
        Paciente = paciente;
    }
}
