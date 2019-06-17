package com.ivanfrescas.notes2gou.utlidades;

public class Utilidades {



    //campos tabla alumnos
    public static final String TABLA_ALUMNO="alumnos";
    //public static final String CAMPO_ID="ID";
    public static final String CAMPO_CORREO="correo";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_ESCUELA="escuela";
    public static final String CAMPO_CONTRASEÑA="contraseña";

    public static final String CREAR_TABLA_ALUMNO="CREATE TABLE "+TABLA_ALUMNO+" ( "

            +CAMPO_CORREO+" TEXT, "
            +CAMPO_NOMBRE+" TEXT, "
            +CAMPO_ESCUELA+" TEXT, "
            +CAMPO_CONTRASEÑA+" TEXT)";


    //Constantes campos tabla crupos
    public static final String TABLA_GRUPOS = "grupos";
    public static final String GRUPOS_NOMBRE = "nombre";
    public static final String GRUPOS_PROFESOR = "profesor";
    public static final String GRUPOS_PAGINA = "paginaweb";
    public static final String GRUPOS_CORREO = "correo";



    public static final String CREAR_TABLA_GRUPOS = "CREATE TABLE "+ TABLA_GRUPOS +" ("+GRUPOS_NOMBRE+" TEXT, "+GRUPOS_PROFESOR+" TEXT, "+GRUPOS_PAGINA+" TEXT, "+GRUPOS_CORREO+" TEXT)" ;

}
