package com.cdlc.term2app2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class PacienteCursorAdapter extends CursorAdapter {

    public PacienteCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.datopaciente   , parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView nombres = (TextView) view.findViewById(R.id.txtNombreS);
        TextView id = (TextView) view.findViewById(R.id.txtId);
        TextView fecha = (TextView) view.findViewById(R.id.txtFecha);
        TextView dias = (TextView) view.findViewById(R.id.txtDias);
        TextView sis = (TextView) view.findViewById(R.id.txtSis);
        TextView Ta = (TextView) view.findViewById(R.id.txtTensionArterial);

        // Extract properties from cursor
        String nombre = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido"));
        String ncompleto= nombre +" "+ apellido;
        String cod= cursor.getString(cursor.getColumnIndexOrThrow("codigo"));
        String diast=  " Diastolica " +cursor.getString(cursor.getColumnIndexOrThrow(DefDB.col_vdiastolico));
        String sist= " Sistolica " +cursor.getString(cursor.getColumnIndexOrThrow(DefDB.col_vsistolico)) ;
        String fech = cursor.getString(cursor.getColumnIndexOrThrow(DefDB.col_fecharegistro));
       // diast + ((sis+dias)/3)
        double ta=  cursor.getColumnIndexOrThrow(DefDB.col_vdiastolico) +(( cursor.getColumnIndexOrThrow(DefDB.col_vsistolico)+ cursor.getColumnIndexOrThrow(DefDB.col_vdiastolico))/3);




        // Populate fields with extracted properties
        nombres.setText(ncompleto);
        id.setText(cod);
        fecha.setText(fech);
        dias.setText(diast);
        sis.setText(sist);
        Ta.setText(" Tension Arterial "+ta);



    }
}
