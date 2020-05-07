package com.cdlc.term2app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActualizarRegistro extends AppCompatActivity implements View.OnClickListener{

    EditText cod, nom, ape, sis, diast;
    Button actualizar, cancelar;
    Paciente est;
    PacienteController c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_registro);




        cod = findViewById(R.id.txtId);
        nom = findViewById(R.id.txtNombre);
        ape = findViewById(R.id.txtApellido);
        sis= findViewById(R.id.txtSistolica);
        diast = findViewById(R.id.txtDiastolica);



        actualizar = findViewById(R.id.btnActualizar);
        actualizar.setOnClickListener(this);

        cancelar = findViewById(R.id.btnCancelar);
        cancelar.setOnClickListener(this);

        c = new PacienteController(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnActualizar:
                if(nom.getText().toString().equals("")|| ape.getText().toString().equals("")||cod.getText().toString().equals("")
                        || diast.getText().toString().equals("")|| sis.getText().toString().equals("") || (Double.parseDouble(diast.getText().toString())> Double.parseDouble(sis.getText().toString())) ){
                    Toast.makeText(this,"Rellene los camppos",Toast.LENGTH_LONG).show();
                    Toast.makeText(this,"Diastolico debe ser menor a sistolico",Toast.LENGTH_LONG).show();

                }else{

                    est = new Paciente(nom.getText().toString(),ape.getText().toString(),cod.getText().toString(), Double.parseDouble(diast.getText().toString()), Double.parseDouble(sis.getText().toString()));
                    Toast.makeText(getApplicationContext(), est.codigo, Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();
                    String code= intent.getStringExtra("codigo");
                    if(c.buscarPaciente(code)){
                        Log.d("Buscar", "No encontrado");
                        long id = c.actualizarPaciente(est,code);
                        Toast.makeText(getApplicationContext(), "Paciente Actualizado", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.d("Buscar", "encontrado");
                        Toast.makeText(getApplicationContext(),"Paciente no esta registrado",Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.btnCancelar:
                Toast.makeText(getApplicationContext(),"Listado",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),Registro.class);
                startActivity(i);
                break;
        }


    }
}
