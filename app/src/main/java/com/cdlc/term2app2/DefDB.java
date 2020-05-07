package com.cdlc.term2app2;

public class DefDB {

    public static final String nameDb = "Salud";
    public static final String tabla_est = "paciente";
    public static final String col_codigo = "codigo";
    public static final String col_nombre = "nombre";
    public static final String col_apellido = "apellido";
    public static final String col_vsistolico = "sistolico";
    public static final String col_vdiastolico = "diastolico";
    public static final String col_fecharegistro = "fechaRegistro";



    public static final String create_tabla_paciente = "CREATE TABLE IF NOT EXISTS paciente(" +
            "  codigo text primary key," +
            "  nombre text," +
            "  apellido text," +
            "  sistolico real," +
            "  diastolico real," +
            "  fechaRegistro text" +
            ");";
}
