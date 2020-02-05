package com.randomgames.shino.bitacora;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.randomgames.shino.bitacora.utilidades.Utilidades;

public class Personal extends AppCompatActivity {

    EditText campoNombre, campoEmail, campoTelefono;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        campoNombre = (EditText) findViewById(R.id.idTxtNombreCompletoPersonal);
        campoEmail = (EditText) findViewById(R.id.idTxtEmailPersonal);
        campoTelefono = (EditText) findViewById(R.id.idTxtTelefonoPersonal);
    }

        public void registrarPersonal(View view){
        ConexionSQLiteHelper connPersonal= new ConexionSQLiteHelper(this,"bd_personal001",null,1);
        SQLiteDatabase db=connPersonal.getWritableDatabase();
            ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_EMAIL,campoEmail.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_PERSONAL001,Utilidades.CAMPO_NOMBRE,values);
            Toast.makeText(getApplicationContext(),"id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
        }

        public void toEntriesPersonal(View view){
            Intent toPersonalConsultaView = new Intent (Personal.this, PersonalConsulta.class);
            startActivity(toPersonalConsultaView);

        }
}

