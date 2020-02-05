package com.randomgames.shino.bitacora;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.randomgames.shino.bitacora.entidades.BaseDeDatosDispositivosFinales;
import com.randomgames.shino.bitacora.entidades.BaseDeDatosDispositivosIntermedios;
import com.randomgames.shino.bitacora.utilidades.Utilidades;

import java.util.ArrayList;

public class Conexiones extends AppCompatActivity {

    EditText txtConexiones,txtId;

    Spinner comboDispositivosFinales, comboDispositivosIniciales;
    String datosDispIni, datosDispFinal;

    ArrayList<String> listaDispFinal;
    ArrayList<BaseDeDatosDispositivosFinales> dispositivosFinalList;

    ConexionSQLiteHelper connDipsFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexiones);

        connDipsFinal = new ConexionSQLiteHelper(getApplicationContext(),"bd_dispositivosFinales001",null,1);

        //txtId = (EditText) findViewById(R.id.idIdentificador);
        txtConexiones = (EditText) findViewById(R.id.idConexion);
        comboDispositivosIniciales = (Spinner) findViewById(R.id.idSpinnerOrigen);
        comboDispositivosFinales = (Spinner) findViewById(R.id.idSpinnerDestino);

        consultarListaDispFinal();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this, android.R.layout.simple_spinner_item,listaDispFinal);


        comboDispositivosIniciales.setAdapter(adaptador);

        comboDispositivosIniciales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id1) {

                if(position != 0) {
                    datosDispIni = dispositivosFinalList.get(position - 1).getTipo().toString()
                    + " - " +dispositivosFinalList.get(position - 1).getNumeroSerie().toString()
                    + " - " +dispositivosFinalList.get(position - 1).getModelo().toString();

                }else{
                    datosDispIni = "";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adaptador2=new ArrayAdapter
                (this, android.R.layout.simple_spinner_item,listaDispFinal);


        comboDispositivosFinales.setAdapter(adaptador2);

        comboDispositivosFinales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id1) {

                if(position != 0) {
                    datosDispFinal = dispositivosFinalList.get(position - 1).getTipo().toString()
                            + " - " +dispositivosFinalList.get(position - 1).getNumeroSerie().toString()
                            + " - " +dispositivosFinalList.get(position - 1).getModelo().toString();

                }else{
                    datosDispFinal = "";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void consultarListaDispFinal() {
        SQLiteDatabase db =connDipsFinal.getReadableDatabase();

        BaseDeDatosDispositivosFinales name = null;
        dispositivosFinalList = new ArrayList<BaseDeDatosDispositivosFinales>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_DISPOSITIVOS_FINALES001,null);

        while(cursor.moveToNext()){
            name=new BaseDeDatosDispositivosFinales();
            name.setTipo(cursor.getString(0));
            name.setNumeroSerie(cursor.getString(1));
            name.setModelo(cursor.getString(2));

            dispositivosFinalList.add(name);
        }
        obtenerLista();
    }

    private void obtenerLista() {

        listaDispFinal = new ArrayList<String>();
        listaDispFinal.add("Seleccione");

        for(int i=0; i<dispositivosFinalList.size();i++){
            listaDispFinal.add(dispositivosFinalList.get(i).getTipo()+" - "+dispositivosFinalList.get(i).getNumeroSerie()+" - "+dispositivosFinalList.get(i).getModelo());
        }
    }

    public void btnRegConexiones(View view){
        ConexionSQLiteHelper connConexiones=new ConexionSQLiteHelper(this,"bd_conexiones001",null,1);

        SQLiteDatabase db=connConexiones.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Utilidades.CAMPO_ID_CONEXIONES, txtId.getText().toString());
        values.put(Utilidades.CAMPO_CONEXION_ORIGEN,datosDispIni);
        values.put(Utilidades.CAMPO_CONEXION_DESTINO,datosDispFinal);
        values.put(Utilidades.CAMPO_MEDIO,txtConexiones.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_CONEXIONES001,Utilidades.CAMPO_ID_CONEXIONES,values);
        Toast.makeText(getApplicationContext(),"id Registro: "+idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void btnConsultas(View view){
        Intent toConexionesConsultaView = new Intent(Conexiones.this, ConexionesConsulta.class);
        startActivity(toConexionesConsultaView);
    }
}
