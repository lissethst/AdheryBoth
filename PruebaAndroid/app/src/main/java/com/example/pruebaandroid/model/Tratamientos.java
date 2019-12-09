package com.example.pruebaandroid.model;

public class Tratamientos {

    public String getIdPaciente() {
        return IdPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        IdPaciente = idPaciente;
    }

    public String getIdMedico() {
        return IdMedico;
    }

    public void setIdMedico(String idMedico) {
        IdMedico = idMedico;
    }

    public String getFechaReg() {
        return FechaReg;
    }

    public void setFechaReg(String fechaReg) {
        FechaReg = fechaReg;
    }

    private String IdPaciente;
    private String IdMedico;
    private String FechaReg;

    public String getNombrePaciente() {
        return NombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        NombrePaciente = nombrePaciente;
    }

    private String NombrePaciente;





    public Tratamientos(){

    }
}
