package com.cdlc.term2app2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PacienteController extends AppCompatActivity {

    private BaseDatos db;

    public PacienteController(Context context) {
        this.db = new BaseDatos(context);
    }

    public long AgregarPaciente(Paciente e){
        try{

            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues valores = new ContentValues();

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(c.getTime());

            valores.put(DefDB.col_codigo,e.codigo);
            valores.put(DefDB.col_nombre,e.nombre);
            valores.put(DefDB.col_apellido,e.apellido);
            valores.put(DefDB.col_vdiastolico,e.diastolica);
            valores.put(DefDB.col_vsistolico,e.sistolica);
            valores.put(DefDB.col_fecharegistro, strDate);
            long id = database.insert(DefDB.tabla_est,null,valores);
            return id;
        }
        catch (Exception ex){
            System.out.println("Error al insertar");
            return 0;
        }
    }


    public boolean buscarPaciente(String cod){
        String[] args = new String[] {cod};
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor c = database.query(DefDB.tabla_est, null, "codigo=?", args, null, null, null);
        if (c.getCount()>0) {
            database.close();
            return true;
        }
        else{
            database.close();
            return false;}
    }

    public long actualizarPaciente(Paciente e, String codAnt){

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());

        try{
            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues valores = new ContentValues();
            String[] args = new String[] {codAnt};
            valores.put(DefDB.col_nombre,e.nombre);
            valores.put(DefDB.col_apellido,e.apellido);
            valores.put(DefDB.col_codigo,e.codigo);
            valores.put(DefDB.col_vdiastolico,e.diastolica);
            valores.put(DefDB.col_vsistolico,e.sistolica);
            valores.put(DefDB.col_fecharegistro, strDate);
            long id = database.update(DefDB.tabla_est, valores,"codigo=?",args);
            database.close();
            return id;
        }
        catch (Exception ex){
            System.out.println("Error al actualizar");
            return 0;
        }

    }
    public Cursor allPacientes() {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            Cursor cur = database.rawQuery("select nombre as _id , apellido, codigo, sistolico, diastolico, fechaRegistro from paciente", null);
            return cur;
        } catch (Exception ex) {
            System.out.println("Error al consultar");
            return null;
        }
    }

    public long Eliminar(String cod){

            try {
                if(buscarPaciente(cod)){
                String[] args = new String[] {cod};
                SQLiteDatabase database = db.getWritableDatabase();
                long id = database.delete(DefDB.tabla_est,"codigo=?",args);
                database.close();
                return id;

                }else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setTitle("Importante");
                    dialog.setMessage("No se encontro elemento a borrar");
                        return 0;
                     }

            }catch (Exception ex){
                return 0;
            }

    }

    public void EliminarTodo(){
        try {

            SQLiteDatabase database = db.getWritableDatabase();
            database.delete(DefDB.tabla_est,null,null);

        }catch (Exception e){

        }
    }

}
