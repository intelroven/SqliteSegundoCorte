package com.cdlc.term2app2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.DialogInterface;


public class Registro extends AppCompatActivity implements View.OnClickListener{

    EditText cod, nom, ape, sis, diast;
    Button guardar, listado;
    //   Button actualizar, consultar;
    Paciente est;
    PacienteController c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        cod = findViewById(R.id.txtId);
        nom = findViewById(R.id.txtNombre);
        ape = findViewById(R.id.txtApellido);
        sis= findViewById(R.id.txtSistolica);
        diast = findViewById(R.id.txtDiastolica);

        guardar = findViewById(R.id.btnRegistrar);
        guardar.setOnClickListener(this);

        listado = findViewById(R.id.btnListar);
        listado.setOnClickListener(this);

        c = new PacienteController(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnRegistrar:
                if(nom.getText().toString().equals("")|| ape.getText().toString().equals("")||cod.getText().toString().equals("")
                        || diast.getText().toString().equals("")|| sis.getText().toString().equals("") || (Double.parseDouble(diast.getText().toString())> Double.parseDouble(sis.getText().toString())) ){
                    Toast.makeText(this,"Rellene los camppos",Toast.LENGTH_LONG).show();
                    Toast.makeText(this,"Diastolico debe ser menor a sistolico",Toast.LENGTH_LONG).show();

                }else{

                    est = new Paciente(nom.getText().toString(),ape.getText().toString(),cod.getText().toString(), Double.parseDouble(diast.getText().toString()), Double.parseDouble(sis.getText().toString()));
                    Toast.makeText(getApplicationContext(), est.codigo, Toast.LENGTH_SHORT).show();

                    if(!c.buscarPaciente(est.codigo)) {
                        Log.d("Buscar", "No encontrado");
                        long id = c.AgregarPaciente(est);
                        Toast.makeText(getApplicationContext(), "Paciente registrado", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.d("Buscar", "encontrado");
                        Toast.makeText(getApplicationContext(),"Paciente ya esta registrado",Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.btnListar:
                Toast.makeText(getApplicationContext(),"Listado",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),Listado.class);
                startActivity(i);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mn_removeAll:
                c.EliminarTodo();
                Toast.makeText(getApplicationContext(),"Listado",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),Listado.class);
                startActivity(i);
                break;
            case R.id.mn_removerElemento:
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(this);
                dialog2.setTitle("Ingrese el codigo del paciente");
                //dialog3.setMessage("No se encontro elemento a borrar");
                dialog2.setCancelable(true);

                final EditText input = new EditText(this);
                input.setHeight(130);
                input.setWidth(340);
                input.setGravity(Gravity.LEFT);
                input.setImeOptions(EditorInfo.IME_ACTION_DONE);


                dialog2.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogo1, int id) {
                      String cod=  input.getText().toString();
                      c.Eliminar(cod);
                    }
                });
                dialog2.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Intent i = new Intent(getApplicationContext(),Listado.class);
                        startActivity(i);
                    }
                });

                dialog2.setView(input);
                dialog2.show();

                break;

            case R.id.mn_update:

                AlertDialog.Builder dialog3 = new AlertDialog.Builder(this);
                dialog3.setTitle("Ingrese el codigo del paciente");
                //dialog3.setMessage("No se encontro elemento a borrar");
                dialog3.setCancelable(true);

                final EditText input2 = new EditText(this);
                input2.setHeight(130);
                input2.setWidth(340);
                input2.setGravity(Gravity.LEFT);
                input2.setImeOptions(EditorInfo.IME_ACTION_DONE);


                dialog3.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogo1, int id) {
                        String cod=  input2.getText().toString();
                        if(c.buscarPaciente(cod)){
                            Toast.makeText(Registro.this,"Actualizar datos",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),ActualizarRegistro.class);
                            i.putExtra("codigo",cod);
                            startActivity(i);
                        }else{
                            AlertDialog.Builder dialog4 = new AlertDialog.Builder(Registro.this);
                            dialog4.setTitle("No existe el codigo del paciente");
                            dialog4.setMessage("No se encontro elemento a actualizar");
                            dialog4.setCancelable(true);
                            dialog4.show();

                        }

                    }
                });
                dialog3.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Intent i = new Intent(getApplicationContext(),Listado.class);
                        startActivity(i);
                    }
                });

                dialog3.setView(input2);
                dialog3.show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }








}
