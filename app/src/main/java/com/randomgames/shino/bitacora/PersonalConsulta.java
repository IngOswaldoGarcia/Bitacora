package com.randomgames.shino.bitacora;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.randomgames.shino.bitacora.entidades.BaseDeDatosPersonal;
import com.randomgames.shino.bitacora.utilidades.Utilidades;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;

public class PersonalConsulta extends AppCompatActivity {

    Spinner comboPersonal;
    TextView txtID,txtNombre,txtEmail,txtTelefono;
    Button btnModificarPersonal;
    ArrayList<String> listaPersonal;
    ArrayList<BaseDeDatosPersonal> personalList;

    ConexionSQLiteHelper connPersonal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_consulta);

        connPersonal = new ConexionSQLiteHelper(getApplicationContext(),"bd_personal001",null,1);

        txtID = (TextView) findViewById(R.id.idTxtIdPersonalConsultas);
        txtNombre = (TextView) findViewById(R.id.idTxtNombrePersonalConsultas);
        txtEmail = (TextView) findViewById(R.id.idTxtEmailPersonalConsultas);
        txtTelefono = (TextView) findViewById(R.id.idTxtTelefonoPersonalConsulta);
        btnModificarPersonal = (Button) findViewById(R.id.idBtnPersonalModificar);
        comboPersonal = (Spinner) findViewById(R.id.idSpinnerPersonalConsultas);

        consultarListaPersonal();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this, android.R.layout.simple_spinner_item,listaPersonal);
        comboPersonal.setAdapter(adaptador);

        comboPersonal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id1) {

                if(position != 0) {
                    txtID.setText(personalList.get(position - 1).getId().toString());
                    txtNombre.setText(personalList.get(position - 1).getNombre().toString());
                    txtEmail.setText(personalList.get(position - 1).getEmail().toString());
                    txtTelefono.setText(personalList.get(position - 1).getTelefono().toString());

                    btnModificarPersonal.setEnabled(true);
                }else{
                    txtID.setText("");
                    txtNombre.setText("");
                    txtEmail.setText("");
                    txtTelefono.setText("");
                    btnModificarPersonal.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void consultarListaPersonal(){
        SQLiteDatabase db = connPersonal.getReadableDatabase();
        BaseDeDatosPersonal name = null;
        personalList = new ArrayList<BaseDeDatosPersonal>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_PERSONAL001,null);

        while(cursor.moveToNext()){
            name=new BaseDeDatosPersonal();
            name.setId(cursor.getInt(0));
            name.setNombre(cursor.getString(1));
            name.setEmail(cursor.getString(2));
            name.setTelefono(cursor.getString(3));
            personalList.add(name);
        }
        obtenerLista();
    }

    private void obtenerLista(){
        listaPersonal = new ArrayList<String>();
        listaPersonal.add("Seleccione");

        for(int i=0; i<personalList.size();i++){
            listaPersonal.add(personalList.get(i).getId()+" - " + personalList.get(i).getNombre()+" - "+personalList.get(i).getEmail()+" - "+personalList.get(i).getTelefono());

        }
    }

    public void modificarDatos(View view){

        String id = String.valueOf(txtID.getText().toString());
        String nombre = String.valueOf(txtNombre.getText().toString());
        String email = String.valueOf(txtEmail.getText().toString());
        String telefono = String.valueOf(txtTelefono.getText().toString());

        Intent intent = new Intent(PersonalConsulta.this, PersonalModificacion.class);
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("nombre",nombre);
        bundle.putString("email",email);
        bundle.putString("telefono",telefono);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
