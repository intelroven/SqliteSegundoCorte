package com.cdlc.term2app2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class Listado extends AppCompatActivity {

    PacienteController c;
    ListView listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listado = findViewById(R.id.listado);
        c = new PacienteController(getApplicationContext());

        Cursor cur = c.allPacientes();

        PacienteCursorAdapter eca = new PacienteCursorAdapter(this,cur,0);
        listado.setAdapter(eca);
        eca.notifyDataSetChanged();
    }
}
