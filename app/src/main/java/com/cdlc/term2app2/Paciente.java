package com.cdlc.term2app2;

public class Paciente {

    public String nombre;
    public String apellido;
    public String codigo;
    public double diastolica;
    public double sistolica;
    public double presionarterial;

    // diast + ((sis+dias)/3)

    public Paciente(String nombre, String apellido, String codigo, double diastolica, double sistolica) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigo = codigo;
        this.diastolica = diastolica;
        this.sistolica = sistolica;
        this.presionarterial = diastolica + ((sistolica+diastolica)/3);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getDiastolica() {
        return diastolica;
    }

    public void setDiastolica(double diastolica) {
        this.diastolica = diastolica;
    }

    public double getSistolica() {
        return sistolica;
    }

    public void setSistolica(double sistolica) {
        this.sistolica = sistolica;
    }

    public double getPresionarterial() {
        return presionarterial;
    }

    public void setPresionarterial(double presionarterial) {
        this.presionarterial = presionarterial;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", codigo='" + codigo + '\'' +
                ", diastolica=" + diastolica +
                ", sistolica=" + sistolica +
                ", presionarterial=" + presionarterial +
                '}';
    }
}
