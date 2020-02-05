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

import com.randomgames.shino.bitacora.entidades.BaseDeDatosDispositivosFinales;
import com.randomgames.shino.bitacora.utilidades.Utilidades;

public class DispositivosFinales extends AppCompatActivity {

    EditText campoNombre, campoNumSerie, campoModelo, campoUbiGeoLatitud, campoUbiGeoLongitud, campoSisOpera, campoPropietario, campoCredenAccesoUser, campoCredenAccesoContraseña;
    String latitud1="", longitud1="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos_finales);

        campoNombre = (EditText) findViewById(R.id.idTipoDispFinal);
        campoNumSerie = (EditText) findViewById(R.id.idNumSerieFinal);
        campoModelo = (EditText) findViewById(R.id.idModeloFinal);
        campoSisOpera = (EditText) findViewById(R.id.idSisOperaFinal);
        campoPropietario = (EditText) findViewById(R.id.idPropietarioFinal);
        campoCredenAccesoUser = (EditText) findViewById(R.id.idCredenAccesoFinal);
        campoCredenAccesoContraseña = (EditText) findViewById(R.id.idCredenAccesoContraseñaFinal);
        campoUbiGeoLatitud = (EditText) findViewById(R.id.idUbicGeoFinal);
        campoUbiGeoLongitud = (EditText) findViewById(R.id.idUbicGeoLongFinal);

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
        ConexionSQLiteHelper connDipsInterFinal=new ConexionSQLiteHelper(this,"bd_dispositivosFinales001",null,1);

        SQLiteDatabase db=connDipsInterFinal.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_TIPO_DISPOSITIVO_FINAL,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_NUMERO_SERIE_FINAL,campoNumSerie.getText().toString());
        values.put(Utilidades.CAMPO_MODELO_FINAL,campoModelo.getText().toString());
        values.put(Utilidades.CAMPO_SISTEMAOPERATIVO_FINAL,campoSisOpera.getText().toString());
        values.put(Utilidades.CAMPO_CREDENCIALES_ACCESO_USER_FINAL,campoCredenAccesoUser.getText().toString());
        values.put(Utilidades.CAMPO_CREDENCIALES_ACCESO_CONTRASEÑA_FINAL,campoCredenAccesoContraseña.getText().toString());
        values.put(Utilidades.CAMPO_PROPIETARIO_FINAL,campoPropietario.getText().toString());
        values.put(Utilidades.CAMPO_UBICACION_GEOGRAFICA_LATITUD_FINAL,campoUbiGeoLatitud.getText().toString());
        values.put(Utilidades.CAMPO_UBICACION_GEOGRAFICA_LONGITUD_FINAL,campoUbiGeoLongitud.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_DISPOSITIVOS_FINALES001,Utilidades.CAMPO_ID_DISPOSITIVO_FINAL,values);
        Toast.makeText(getApplicationContext(),"id Registro: "+idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void buttonToMiddleDevicesEntries(View view){
        Intent toDispositivosFinalesConsultaView = new Intent(DispositivosFinales.this, DispositivosFinalesConsulta.class);
        startActivity(toDispositivosFinalesConsultaView);

    }

    public void getPosition(View view){
        campoUbiGeoLatitud.setText(latitud1);
        campoUbiGeoLongitud.setText(longitud1);
    }














    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setDispositivosFinales(this);
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
        DispositivosFinales DispositivosFinales;

        public DispositivosFinales getDispositivosFinales() {
            return DispositivosFinales;
        }

        public void setDispositivosFinales(DispositivosFinales DispositivosFinales) {
            this.DispositivosFinales = DispositivosFinales;
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