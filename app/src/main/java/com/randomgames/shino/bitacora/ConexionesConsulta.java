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

import com.randomgames.shino.bitacora.entidades.BaseDeDatosConexiones;
import com.randomgames.shino.bitacora.entidades.BaseDeDatosDispositivosFinales;
import com.randomgames.shino.bitacora.utilidades.Utilidades;

import java.util.ArrayList;

public class ConexionesConsulta extends AppCompatActivity {

    TextView txtId,txtConexionOrigen, txtConexionDestino, txtMedio;
    Spinner comboConexion;
    Button btnModificaciones;

    ArrayList<String> listaConexiones;
    ArrayList<BaseDeDatosConexiones> conexionesList;
    ConexionSQLiteHelper connConexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexiones_consulta);

        connConexion = new ConexionSQLiteHelper(this, "bd_conexiones001", null, 1);

        txtId = (TextView) findViewById(R.id.idIdentificadorConsulta);
        txtConexionOrigen = (TextView) findViewById(R.id.idTxtConexionOrigenConsulta);
        txtConexionDestino = (TextView) findViewById(R.id.idTxtConexionDestinoConsulta);
        btnModificaciones = (Button) findViewById(R.id.idButtonConexionesConsulta);
        txtMedio = (TextView) findViewById(R.id.idTxtMedioConsulta);

        comboConexion = (Spinner) findViewById(R.id.idSpinnerConexionConsulta);

        consultarConexion();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter
                (this, android.R.layout.simple_spinner_item, listaConexiones);
        comboConexion.setAdapter(adaptador);

        comboConexion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id1) {

                if (position != 0) {
                    txtId.setText(conexionesList.get(position - 1).getId().toString());
                    txtConexionOrigen.setText(conexionesList.get(position - 1).getConexionOrigen().toString());
                    txtConexionDestino.setText(conexionesList.get(position - 1).getConexionDestino().toString());
                    txtMedio.setText(conexionesList.get(position - 1).getMedio().toString());
                    btnModificaciones.setEnabled(true);
                } else {
                    txtId.setText("");
                    txtConexionOrigen.setText("");
                    txtConexionDestino.setText("");
                    txtMedio.setText("");
                    btnModificaciones.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void consultarConexion() {
        SQLiteDatabase db = connConexion.getReadableDatabase();

        BaseDeDatosConexiones name = null;
        conexionesList = new ArrayList<BaseDeDatosConexiones>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_CONEXIONES001, null);

        while (cursor.moveToNext()) {
            name = new BaseDeDatosConexiones();
            name.setId(cursor.getInt(0));
            name.setConexionOrigen(cursor.getString(1));
            name.setConexionDestino(cursor.getString(2));
            name.setMedio(cursor.getString(3));

            conexionesList.add(name);
        }
        obtenerLista();
    }

    private void obtenerLista() {

        listaConexiones = new ArrayList<String>();
        listaConexiones.add("Seleccione");

        for (int i = 0; i < conexionesList.size(); i++) {
            listaConexiones.add(conexionesList.get(i).getId() + " - " + conexionesList.get(i).getConexionOrigen() + " - " + conexionesList.get(i).getConexionDestino() + " - " + conexionesList.get(i).getMedio());
        }

    }

    public void actualizarDatos(View view) {
        String id = String.valueOf(txtId.getText().toString());
        String medio = String.valueOf(txtMedio.getText());

        Intent intent = new Intent(ConexionesConsulta.this,ConexionesModificacion2.class);
        Bundle bundle2 = new Bundle();
        bundle2.putString("id",id);
        bundle2.putString("medio",medio);
        intent.putExtras(bundle2);
        startActivity(intent);
    }
}