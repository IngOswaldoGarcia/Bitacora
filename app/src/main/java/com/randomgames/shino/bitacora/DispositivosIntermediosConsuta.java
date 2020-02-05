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

import com.randomgames.shino.bitacora.entidades.BaseDeDatosDispositivosIntermedios;
import com.randomgames.shino.bitacora.utilidades.Utilidades;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DispositivosIntermediosConsuta extends AppCompatActivity {

    Spinner comboDispositivosIntermedios;
    TextView txtIdDispositivoIntermedio,
            txtTipoDispositivo,
            txtNumeroSerie,
            txtModelo,
            txtSistemaOperativo,
            txtArchivoConfiguracion,
            txtCredencialesAccesoUser,
            txtCredencialesAccesoContraseña,
            txtUbicacionGeograficaLatitud,
            txtUbicacionGeograficaLongitud;
    Button btnEditarDispositivo, btnVerUbicacionDispositivo;
    ArrayList<String> listaDispInter;
    ArrayList<BaseDeDatosDispositivosIntermedios> dispositivosList;

    ConexionSQLiteHelper connDipsInter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos_intermedios_consuta);

        connDipsInter = new ConexionSQLiteHelper(getApplicationContext(),"bd_dispositivosIntermedios003",null,1);

        comboDispositivosIntermedios = (Spinner) findViewById(R.id.idSpinnerDispositivoInterConsulta);

        txtIdDispositivoIntermedio = (TextView) findViewById(R.id.idTxtIdIntermedioConsulta);
        txtTipoDispositivo = (TextView) findViewById(R.id.idTxtTipoDispInter);
        txtNumeroSerie = (TextView) findViewById(R.id.idTxtNumSerDispInter);
        txtModelo = (TextView) findViewById(R.id.idTxtModeloDispInter);
        txtSistemaOperativo = (TextView) findViewById(R.id.idTxtSisOperaDispInter);
        txtArchivoConfiguracion = (TextView) findViewById(R.id.idTxtArchConfigDispInter);
        txtCredencialesAccesoUser = (TextView) findViewById(R.id.idTxtCredAccessDispInter);
        txtCredencialesAccesoContraseña = (TextView) findViewById(R.id.idTxtCredAccessDispInterContra);
        txtUbicacionGeograficaLatitud = (TextView) findViewById(R.id.idTxtUbicGeoDispInter);
        txtUbicacionGeograficaLongitud = (TextView) findViewById(R.id.idTxtUbicGeoDispInterLng);

        btnEditarDispositivo = (Button)findViewById(R.id.idBtnToChangeEntries);
        btnVerUbicacionDispositivo = (Button)findViewById(R.id.idBtnToReviewMap);

        consultarListaDispInter();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this, android.R.layout.simple_spinner_item,listaDispInter);
        comboDispositivosIntermedios.setAdapter(adaptador);

        comboDispositivosIntermedios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id1) {

                if(position != 0) {
                    txtIdDispositivoIntermedio.setText((dispositivosList.get(position - 1).getId().toString()));
                    txtTipoDispositivo.setText(dispositivosList.get(position - 1).getNombre().toString());
                    txtNumeroSerie.setText(dispositivosList.get(position - 1).getNumeroSerie().toString());
                    txtModelo.setText(dispositivosList.get(position - 1).getModelo().toString());
                    txtSistemaOperativo.setText(dispositivosList.get(position - 1).getSistemaOperativo().toString());
                    txtArchivoConfiguracion.setText(dispositivosList.get(position - 1).getArchivoDeConfiguracion().toString());
                    txtCredencialesAccesoUser.setText(dispositivosList.get(position - 1).getCredencialesDeAccesoUser().toString());
                    txtCredencialesAccesoContraseña.setText(dispositivosList.get(position - 1).getCredencialesDeAccesoContraseña().toString());
                    txtUbicacionGeograficaLatitud.setText(dispositivosList.get(position - 1).getUbicacionGeograficaLatitud().toString());
                    txtUbicacionGeograficaLongitud.setText(dispositivosList.get(position - 1).getUbicacionGeograficaLongitud().toString());
                    btnEditarDispositivo.setEnabled(true);
                    btnVerUbicacionDispositivo.setEnabled(true);
                }else{
                    txtIdDispositivoIntermedio.setText("");
                    txtTipoDispositivo.setText("");
                    txtNumeroSerie.setText("");
                    txtModelo.setText("");
                    txtSistemaOperativo.setText("");
                    txtArchivoConfiguracion.setText("");
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

    private void consultarListaDispInter() {
        SQLiteDatabase db =connDipsInter.getReadableDatabase();

        BaseDeDatosDispositivosIntermedios name = null;
        dispositivosList = new ArrayList<BaseDeDatosDispositivosIntermedios>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_DISPOSITIVOS_INTERMEDIOS003,null);

        while(cursor.moveToNext()){
            name=new BaseDeDatosDispositivosIntermedios();
            name.setId(cursor.getInt(0));
            name.setNombre(cursor.getString(1));
            name.setNumeroSerie(cursor.getString(2));
            name.setModelo(cursor.getString(3));
            name.setSistemaOperativo(cursor.getString(4));
            name.setArchivoDeConfiguracion(cursor.getString(5));
            name.setCredencialesDeAccesoUser(cursor.getString(6));
            name.setCredencialesDeAccesoContraseña(cursor.getString(7));
            name.setUbicacionGeograficaLatitud(cursor.getString(8));
            name.setUbicacionGeograficaLongitud(cursor.getString(9));

            dispositivosList.add(name);
        }
        obtenerLista();
    }

    private void obtenerLista() {

        listaDispInter = new ArrayList<String>();
        listaDispInter.add("Seleccione");

        for(int i=0; i<dispositivosList.size();i++){
            listaDispInter.add(dispositivosList.get(i).getId()+" - "+ dispositivosList.get(i).getNombre()+" - "+dispositivosList.get(i).getNumeroSerie()+" - "+dispositivosList.get(i).getModelo()+" - "+dispositivosList.get(i).getSistemaOperativo()+" - "+dispositivosList.get(i).getArchivoDeConfiguracion()+" - "+dispositivosList.get(i).getCredencialesDeAccesoUser()+" - "+dispositivosList.get(i).getCredencialesDeAccesoContraseña()+" - "+dispositivosList.get(i).getUbicacionGeograficaLatitud()+" - "+dispositivosList.get(i).getUbicacionGeograficaLongitud());
        }

    }
    public void abrirMapa(View view) {
    double latitud = Double.parseDouble(String.valueOf(txtUbicacionGeograficaLatitud.getText()));
        double longitud = Double.parseDouble(String.valueOf(txtUbicacionGeograficaLongitud.getText()));
    Intent intent =new Intent(DispositivosIntermediosConsuta.this,LocalizacionDispIntermediosConsulta.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("latitud",latitud);
        bundle.putDouble("longitud",longitud);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void actualizarDatos(View view){
        String id = String.valueOf(txtIdDispositivoIntermedio.getText());
        String nombre = String.valueOf(txtTipoDispositivo.getText());
        String numSerie = String.valueOf(txtNumeroSerie.getText());
        String modelo = String.valueOf(txtModelo.getText());
        String sisOperativo = String.valueOf(txtSistemaOperativo.getText());
        String archivoConfiguracion = String.valueOf(txtArchivoConfiguracion.getText());
        String user = String.valueOf(txtCredencialesAccesoUser.getText());
        String contraseña = String.valueOf(txtCredencialesAccesoContraseña.getText());
        String latitudString = String.valueOf(txtUbicacionGeograficaLatitud.getText());
        String longitudString = String.valueOf(txtUbicacionGeograficaLongitud.getText());

        Intent intent = new Intent(DispositivosIntermediosConsuta.this,DispositivosIntermediosModificacion.class);
        Bundle bundle2 = new Bundle();
        bundle2.putString("id",id);
        bundle2.putString("nombre",nombre);
        bundle2.putString("numSerie",numSerie);
        bundle2.putString("modelo",modelo);
        bundle2.putString("sisOperativo",sisOperativo);
        bundle2.putString("archivoConfiguracion",archivoConfiguracion);
        bundle2.putString("user",user);
        bundle2.putString("contraseña",contraseña);
        bundle2.putString("latitudString",latitudString);
        bundle2.putString("longitudString",longitudString);
        intent.putExtras(bundle2);

        startActivity(intent);
    }
}
