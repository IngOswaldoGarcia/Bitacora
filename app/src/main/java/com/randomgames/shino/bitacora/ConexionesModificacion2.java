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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.randomgames.shino.bitacora.entidades.BaseDeDatosDispositivosFinales;
import com.randomgames.shino.bitacora.entidades.BaseDeDatosDispositivosIntermedios;
import com.randomgames.shino.bitacora.utilidades.Utilidades;

import java.util.ArrayList;

public class ConexionesModificacion2 extends AppCompatActivity {

    EditText txtConexiones,txtId;

    Spinner comboDispositivosFinales, comboDispositivosIniciales;
    String datosDispIni, datosDispFinal;

    ArrayList<String> listaDispFinal;
    ArrayList<BaseDeDatosDispositivosFinales> dispositivosFinalList;

    ConexionSQLiteHelper connDipsFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexiones_modificacion2);

        connDipsFinal = new ConexionSQLiteHelper(getApplicationContext(),"bd_dispositivosFinales001",null,1);

        txtId = (EditText) findViewById(R.id.idIdentificadorModificacion);
        txtConexiones = (EditText) findViewById(R.id.idConexionModificacion);

        comboDispositivosIniciales = (Spinner) findViewById(R.id.spinner);
        comboDispositivosFinales = (Spinner) findViewById(R.id.spinner2);

        Bundle bundle2 = getIntent().getExtras();

        if (bundle2 != null) {

            String id = bundle2.getString("id");
            String medio = bundle2.getString("medio");

            txtId.setText(id);
            txtConexiones.setText(medio);
        }

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

    ConexionSQLiteHelper connConexiones=new ConexionSQLiteHelper(this,"bd_conexiones001",null,1);

    public void actualizarDatosConexiones(View view){

        SQLiteDatabase db=connConexiones.getWritableDatabase();
        String[]parametros={txtId.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_CONEXION_ORIGEN,datosDispIni);
        values.put(Utilidades.CAMPO_CONEXION_DESTINO,datosDispFinal);
        values.put(Utilidades.CAMPO_MEDIO,txtConexiones.getText().toString());

        db.update(Utilidades.TABLA_CONEXIONES001,values,Utilidades.CAMPO_ID_CONEXIONES+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Se Actualizo la Conexion", Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void eliminarDatosConexiones(View view){

        SQLiteDatabase db=connConexiones.getWritableDatabase();
        String[]parametros={txtId.getText().toString()};
        db.delete(Utilidades.TABLA_CONEXIONES001,Utilidades.CAMPO_ID_CONEXIONES+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Se elimino el registro", Toast.LENGTH_SHORT).show();
        db.close();

    }

}