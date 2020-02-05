package com.randomgames.shino.bitacora;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.randomgames.shino.bitacora.utilidades.Utilidades;

public class PersonalModificacion extends AppCompatActivity {

    EditText campoID,campoNombre, campoEmail,campoTelefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_modificacion);

        campoID = (EditText) findViewById(R.id.idTxtIdPersonalModificacion);
        campoNombre = (EditText) findViewById(R.id.idTxtNombrePersonalModificacion);
        campoEmail = (EditText) findViewById(R.id.idTxtEmailPersonalModificacion);
        campoTelefono = (EditText) findViewById(R.id.idTxtTelefonoPersonalModificacion);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String id = bundle.getString("id");
            String nombre = bundle.getString("nombre");
            String email = bundle.getString("email");
            String telefono = bundle.getString("telefono");

            campoID.setText(id);
            campoNombre.setText(nombre);
            campoEmail.setText(email);
            campoTelefono.setText(telefono);
        }



    }
    ConexionSQLiteHelper connPersonal = new ConexionSQLiteHelper(this,"bd_personal001",null,1);

    public void actualizarPersona(View view){
        SQLiteDatabase db = connPersonal.getWritableDatabase();
        String[] parametros={campoID.getText().toString()};
        ContentValues values= new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_EMAIL,campoEmail.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());

        db.update(Utilidades.TABLA_PERSONAL001,values,Utilidades.CAMPO_ID_PERSONAL+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Se Actualizo el Personal", Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void borrarPersona(View view){
        SQLiteDatabase db = connPersonal.getWritableDatabase();
        String[] parametros={campoID.getText().toString()};
        db.delete(Utilidades.TABLA_PERSONAL001,Utilidades.CAMPO_ID_PERSONAL+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Se elimino el Personal", Toast.LENGTH_SHORT).show();
        db.close();
    }
}
