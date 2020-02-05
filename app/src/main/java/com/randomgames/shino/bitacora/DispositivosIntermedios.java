package com.randomgames.shino.bitacora;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import android.location.LocationListener;
import android.location.LocationProvider;
import android.provider.Settings;

import android.util.Log;
import android.Manifest;

import com.randomgames.shino.bitacora.entidades.BaseDeDatosDispositivosIntermedios;
import com.randomgames.shino.bitacora.utilidades.Utilidades;

public class DispositivosIntermedios extends AppCompatActivity {

    EditText campoNombre, campoNumSerie, campoModelo, campoUbiGeoLatitud, campoUbiGeoLongitud, campoSisOpera, campoArchiConfi, campoCredenAccesoUser, campoCredenAccesoContraseña;
    String latitud1="", longitud1="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos_intermedios);

        campoNombre = (EditText) findViewById(R.id.idTipoDisp);
        campoNumSerie = (EditText) findViewById(R.id.idNumSerie);
        campoModelo = (EditText) findViewById(R.id.idModelo);
        campoSisOpera = (EditText) findViewById(R.id.idSisOpera);
        campoArchiConfi = (EditText) findViewById(R.id.idArchConfig);
        campoCredenAccesoUser = (EditText) findViewById(R.id.idCredenAcceso);
        campoCredenAccesoContraseña = (EditText) findViewById(R.id.idCredenAccesoContraseña);
        campoUbiGeoLatitud = (EditText) findViewById(R.id.idUbicGeo);
        campoUbiGeoLongitud = (EditText) findViewById(R.id.idUbicGeoLong);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }
    }




    public void onClick(View view){
        registrarUsuarios();
    }

    private void registrarUsuarios(){
        ConexionSQLiteHelper connDipsInter=new ConexionSQLiteHelper(this,"bd_dispositivosIntermedios003",null,1);

        SQLiteDatabase db=connDipsInter.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_TIPO_DISPOSITIVO,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_NUMERO_SERIE,campoNumSerie.getText().toString());
        values.put(Utilidades.CAMPO_MODELO,campoModelo.getText().toString());
        values.put(Utilidades.CAMPO_SISTEMAOPERATIVO,campoSisOpera.getText().toString());
        values.put(Utilidades.CAMPO_ARCHIVO_CONFIGURACION,campoArchiConfi.getText().toString());
        values.put(Utilidades.CAMPO_CREDENCIALES_ACCESO_USER,campoCredenAccesoUser.getText().toString());
        values.put(Utilidades.CAMPO_CREDENCIALES_ACCESO_CONTRASEÑA,campoCredenAccesoContraseña.getText().toString());
        values.put(Utilidades.CAMPO_UBICACION_GEOGRAFICA_LATITUD,campoUbiGeoLatitud.getText().toString());
        values.put(Utilidades.CAMPO_UBICACION_GEOGRAFICA_LONGITUD,campoUbiGeoLongitud.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_DISPOSITIVOS_INTERMEDIOS003,Utilidades.CAMPO_ID_DISPOSITIVO_INTERMEDIO,values);
        Toast.makeText(getApplicationContext(),"id Registro: "+idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void buttonToMiddleDevicesEntries(View view){
        Intent toDispositivosIntermediosConsultaView = new Intent(DispositivosIntermedios.this, DispositivosIntermediosConsuta.class);
        startActivity(toDispositivosIntermediosConsultaView);

    }

    public void getPosition(View view){
        campoUbiGeoLatitud.setText(latitud1);
        campoUbiGeoLongitud.setText(longitud1);
    }














    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setDispositivosIntermedios(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }


    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        DispositivosIntermedios DispositivosIntermedios;

        public DispositivosIntermedios getDispositivosIntermedios() {
            return DispositivosIntermedios;
        }

        public void setDispositivosIntermedios(DispositivosIntermedios DispositivosIntermedios) {
            this.DispositivosIntermedios = DispositivosIntermedios;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion

            loc.getLatitude();
            loc.getLongitude();

            latitud1 = String.valueOf(loc.getLatitude());
            longitud1 = String.valueOf(loc.getLongitude());
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            //mensaje1.setText("GPS Desactivado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            //mensaje1.setText("GPS Activado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }
}




