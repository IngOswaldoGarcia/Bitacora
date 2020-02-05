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

import com.randomgames.shino.bitacora.entidades.BaseDeDatosDispositivosFinales;
import com.randomgames.shino.bitacora.entidades.BaseDeDatosDispositivosIntermedios;
import com.randomgames.shino.bitacora.utilidades.Utilidades;

import java.util.ArrayList;

public class DispositivosFinalesConsulta extends AppCompatActivity {

    Spinner comboDispositivosFinales;
    TextView txtIdDispositivoFinalConsulta,
            txtTipoDispositivo,
            txtNumeroSerie,
            txtModelo,
            txtSistemaOperativo,
            txtPropietario,
            txtCredencialesAccesoUser,
            txtCredencialesAccesoContraseña,
            txtUbicacionGeograficaLatitud,
            txtUbicacionGeograficaLongitud;
    Button btnEditarDispositivo, btnVerUbicacionDispositivo;
    ArrayList<String> listaDispFinal;
    ArrayList<BaseDeDatosDispositivosFinales> dispositivosListFinal;

    ConexionSQLiteHelper connDipsFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos_finales_consulta);

        connDipsFinal = new ConexionSQLiteHelper(getApplicationContext(),"bd_dispositivosFinales001",null,1);

        comboDispositivosFinales = (Spinner) findViewById(R.id.idSpinnerDispositivoFinalConsulta);

        txtIdDispositivoFinalConsulta = (TextView) findViewById(R.id.idTxtIdDispositivoFinalConsulta);
        txtTipoDispositivo = (TextView) findViewById(R.id.idTxtTipoDispFinal);
        txtNumeroSerie = (TextView) findViewById(R.id.idTxtNumSerDispFinal);
        txtModelo = (TextView) findViewById(R.id.idTxtModeloDispFinal);
        txtSistemaOperativo = (TextView) findViewById(R.id.idTxtSisOperaDispFinal);
        txtPropietario = (TextView) findViewById(R.id.idTxtPropietarioFinal);
        txtCredencialesAccesoUser = (TextView) findViewById(R.id.idTxtCredAccessDispFinal);
        txtCredencialesAccesoContraseña = (TextView) findViewById(R.id.idTxtCredAccessDispFinalContra);
        txtUbicacionGeograficaLatitud = (TextView) findViewById(R.id.idTxtUbicGeoDispFinal);
        txtUbicacionGeograficaLongitud = (TextView) findViewById(R.id.idTxtUbicGeoDispFinalLng);

        btnEditarDispositivo = (Button)findViewById(R.id.idBtnToChangeEntriesFinal);
        btnVerUbicacionDispositivo = (Button)findViewById(R.id.idBtnToReviewMapFinal);

        consultarListaDispFinal();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this, android.R.layout.simple_spinner_item,listaDispFinal);
        comboDispositivosFinales.setAdapter(adaptador);

        comboDispositivosFinales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id1) {

                if(position != 0) {
                    txtIdDispositivoFinalConsulta.setText(dispositivosListFinal.get(position - 1).getId().toString());
                    txtTipoDispositivo.setText(dispositivosListFinal.get(position - 1).getTipo().toString());
                    txtNumeroSerie.setText(dispositivosListFinal.get(position - 1).getNumeroSerie().toString());
                    txtModelo.setText(dispositivosListFinal.get(position - 1).getModelo().toString());
                    txtSistemaOperativo.setText(dispositivosListFinal.get(position - 1).getSistemaOperativo().toString());
                    txtCredencialesAccesoUser.setText(dispositivosListFinal.get(position - 1).getCredencialesDeAccesoUser().toString());
                    txtCredencialesAccesoContraseña.setText(dispositivosListFinal.get(position - 1).getCredencialesDeAccesoContraseña().toString());
                    txtPropietario.setText(dispositivosListFinal.get(position - 1).getPropietario().toString());
                    txtUbicacionGeograficaLatitud.setText(dispositivosListFinal.get(position - 1).getUbicacionGeograficaLatitud().toString());
                    txtUbicacionGeograficaLongitud.setText(dispositivosListFinal.get(position - 1).getUbicacionGeograficaLongitud().toString());
                    btnEditarDispositivo.setEnabled(true);
                    btnVerUbicacionDispositivo.setEnabled(true);
                }else{
                    txtIdDispositivoFinalConsulta.setText("");
                    txtTipoDispositivo.setText("");
                    txtNumeroSerie.setText("");
                    txtModelo.setText("");
                    txtSistemaOperativo.setText("");
                    txtPropietario.setText("");
                    txtCredencialesAccesoUser.setText("");
                    txtCredencialesAccesoContraseña.setText("");
                    txtUbicacionGeograficaLatitud.setText("");
                    txtUbicacionGeograficaLongitud.setText("");
                    btnEditarDispositivo.setEnabled(false);
                    btnVerUbicacionDispositivo.setEnabled(false);
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
        dispositivosListFinal = new ArrayList<BaseDeDatosDispositivosFinales>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_DISPOSITIVOS_FINALES001,null);

        while(cursor.moveToNext()){
            name=new BaseDeDatosDispositivosFinales();
            name.setId(cursor.getInt(0));
            name.setTipo(cursor.getString(1));
            name.setNumeroSerie(cursor.getString(2));
            name.setModelo(cursor.getString(3));
            name.setSistemaOperativo(cursor.getString(4));
            name.setCredencialesDeAccesoUser(cursor.getString(5));
            name.setCredencialesDeAccesoContraseña(cursor.getString(6));
            name.setPropietario(cursor.getString(7));
            name.setUbicacionGeograficaLatitud(cursor.getString(8));
            name.setUbicacionGeograficaLongitud(cursor.getString(9));

            dispositivosListFinal.add(name);
        }
        obtenerLista();
    }

    private void obtenerLista() {

        listaDispFinal = new ArrayList<String>();
        listaDispFinal.add("Seleccione");

        for(int i=0; i<dispositivosListFinal.size();i++){
            listaDispFinal.add(dispositivosListFinal.get(i).getId()+" - "+ dispositivosListFinal.get(i).getTipo()+" - "+dispositivosListFinal.get(i).getNumeroSerie()+" - "+dispositivosListFinal.get(i).getModelo()+" - "+dispositivosListFinal.get(i).getSistemaOperativo()+" - "+dispositivosListFinal.get(i).getCredencialesDeAccesoUser()+" - "+dispositivosListFinal.get(i).getCredencialesDeAccesoContraseña()+" - "+dispositivosListFinal.get(i).getPropietario()+" - "+dispositivosListFinal.get(i).getUbicacionGeograficaLatitud()+" - "+dispositivosListFinal.get(i).getUbicacionGeograficaLongitud());
        }

    }
    public void abrirMapa(View view) {
        double latitud = Double.parseDouble(String.valueOf(txtUbicacionGeograficaLatitud.getText()));
        double longitud = Double.parseDouble(String.valueOf(txtUbicacionGeograficaLongitud.getText()));
        Intent intent =new Intent(DispositivosFinalesConsulta.this,LocalizacionDispFinalesConsulta.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("latitud",latitud);
        bundle.putDouble("longitud",longitud);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void actualizarDatos(View view){
        String id = String.valueOf(txtIdDispositivoFinalConsulta.getText());
        String nombre = String.valueOf(txtTipoDispositivo.getText());
        String numSerie = String.valueOf(txtNumeroSerie.getText());
        String modelo = String.valueOf(txtModelo.getText());
        String sisOperativo = String.valueOf(txtSistemaOperativo.getText());
        String user = String.valueOf(txtCredencialesAccesoUser.getText());
        String contraseña = String.valueOf(txtCredencialesAccesoContraseña.getText());
        String propietario = String.valueOf(txtPropietario.getText());
        String latitudString = String.valueOf(txtUbicacionGeograficaLatitud.getText());
        String longitudString = String.valueOf(txtUbicacionGeograficaLongitud.getText());

        Intent intent = new Intent(DispositivosFinalesConsulta.this,DispositivosFinalesModificacion.class);
        Bundle bundle2 = new Bundle();
        bundle2.putString("id",id);
        bundle2.putString("nombre",nombre);
        bundle2.putString("numSerie",numSerie);
        bundle2.putString("modelo",modelo);
        bundle2.putString("sisOperativo",sisOperativo);
        bundle2.putString("user",user);
        bundle2.putString("contraseña",contraseña);
        bundle2.putString("propietario",propietario);
        bundle2.putString("latitudString",latitudString);
        bundle2.putString("longitudString",longitudString);
        intent.putExtras(bundle2);

        startActivity(intent);
    }
}