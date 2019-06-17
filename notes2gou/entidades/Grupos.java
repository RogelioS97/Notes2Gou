package com.ivanfrescas.notes2gou.entidades;

import java.io.Serializable;

public class Grupos implements Serializable {

    private String nombre;
    private String profesor;
    private String correo;
    private String paginaweb;




    public Grupos() {
    }

    public Grupos(String nombre, String profesor, String correo, String paginaweb) {

        this.nombre = nombre;
        this.profesor = profesor;
        this.correo = correo;
        this.paginaweb = paginaweb;


    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPaginaweb() {
        return paginaweb;
    }

    public void setPaginaweb(String paginaweb) {
        this.paginaweb = paginaweb;
    }


}
