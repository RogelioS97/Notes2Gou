package com.ivanfrescas.notes2gou.entidades;

public class Alumno {

//private int id;
private String correo;
private String nombre;
private String escuela;
private String contraseña;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }



    public Alumno( String correo, String nombre, String escuela, String contraseña) {

        this.correo = correo;
        this.nombre = nombre;
        this.escuela = escuela;
        this.contraseña = contraseña;



    }
}
