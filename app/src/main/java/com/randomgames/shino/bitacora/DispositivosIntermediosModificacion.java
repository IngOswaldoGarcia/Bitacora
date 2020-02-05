package com.randomgames.shino.bitacora;

import android.*;
import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.randomgames.shino.bitacora.utilidades.Utilidades;

public class DispositivosIntermediosModificacion extends AppCompatActivity {

    EditText campoId,campoNombre, campoNumSerie, campoModelo, campoUbiGeoLatitud, campoUbiGeoLongitud, campoSisOpera, campoArchiConfi, campoCredenAccesoUser, campoCredenAccesoContraseña;
    Button btnEliminar, btnActualizar,btnRegistrarDisp;
    String latitud1="", longitud1="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos_intermedios_modificacion);

        campoId = (EditText) findViewById(R.id.idDispositivoIntermedioModificacion);
        campoNombre = (EditText) findViewById(R.id.idTipoDisp);
        campoNumSerie = (EditText) findViewById(R.id.idNumSerie);
        campoModelo = (EditText) findViewById(R.id.idModelo);
        campoSisOpera = (EditText) findViewById(R.id.idSisOpera);
        campoArchiConfi = (EditText) findViewById(R.id.idArchConfig);
        campoCredenAccesoUser = (EditText) findViewById(R.id.idCredenAcceso);
        campoCredenAccesoContraseña = (EditText) findViewById(R.id.idCredenAccesoContraseña);
        campoUbiGeoLatitud = (EditText) findViewById(R.id.idUbicGeo);
        campoUbiGeoLongitud = (EditText) findViewById(R.id.idUbicGeoLong);

        btnEliminar = (Button) findViewById(R.id.idButtonToEliminateDevice);
        btnActualizar = (Button) findViewById(R.id.idButtonToUpdateDivice);
        btnRegistrarDisp = (Button) findViewById(R.id.idRegisDisp);

        Bundle bundle2 = getIntent().getExtras();

        if (bundle2 != null) {

                String id = bundle2.getString("id");
                String nombre = bundle2.getString("nombre");
                String numSerie = bundle2.getString("numSerie");
                String modelo = bundle2.getString("modelo");
                String sisOperativo = bundle2.getString("sisOperativo");
                String archivoConfiguracion = bundle2.getString("archivoConfiguracion");
                String user = bundle2.getString("user");
                String contraseña = bundle2.getString("contraseña");
                String latitudString = bundle2.getString("latitudString");
                String longitudString = bundle2.getString("longitudString");


                campoId.setText(id);
                campoNombre.setText(nombre);
                campoNumSerie.setText(numSerie);
                campoModelo.setText(modelo);
                campoSisOpera.setText(sisOperativo);
                campoArchiConfi.setText(archivoConfiguracion);
                campoCredenAccesoUser.setText(user);
                campoCredenAccesoContraseña.setText(contraseña);
                campoUbiGeoLatitud.setText(latitudString);
                campoUbiGeoLongitud.setText(longitudString);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }

    }

    ConexionSQLiteHelper connDipsInter=new ConexionSQLiteHelper(this,"bd_dispositivosIntermedios003",null,1);

    public void actualizarDispositivo(View view){

            SQLiteDatabase db=connDipsInter.getWritableDatabase();
            String[]parametros={campoId.getText().toString()};
            ContentValues values=new ContentValues();
            values.put(Utilidades.CAMPO_TIPO_DISPOSITIVO,campoNombre.getText().toString());
            values.put(Utilidades.CAMPO_NUMERO_SERIE,campoNumSerie.getText().toString());
            values.put(Utilidades.CAMPO_MODELO,campoModelo.getText().toString());
            values.put(Utilidades.CAMPO_SISTEMAOPERATIVO,campoSisOpera.getText().toString());
            values.put(Utilidades.CAMPO_CREDENCIALES_ACCESO_USER,campoCredenAccesoUser.getText().toString());
            values.put(Utilidades.CAMPO_CREDENCIALES_ACCESO_CONTRASEÑA,campoCredenAccesoContraseña.getText().toString());
            values.put(Utilidades.CAMPO_ARCHIVO_CONFIGURACION,campoArchiConfi.getText().toString());
            values.put(Utilidades.CAMPO_UBICACION_GEOGRAFICA_LATITUD,campoUbiGeoLatitud.getText().toString());
            values.put(Utilidades.CAMPO_UBICACION_GEOGRAFICA_LONGITUD,campoUbiGeoLongitud.getText().toString());

            db.update(Utilidades.TABLA_DISPOSITIVOS_INTERMEDIOS003,values,Utilidades.CAMPO_ID_DISPOSITIVO_INTERMEDIO+"=?",parametros);
            Toast.makeText(getApplicationContext(), "Se Actualizo el Dispositivo", Toast.LENGTH_SHORT).show();
            db.close();
    }

        public void eliminarDispositivo(View view){
            SQLiteDatabase db=connDipsInter.getWritableDatabase();
            String[]parametros={campoId.getText().toString()};
            db.delete(Utilidades.TABLA_DISPOSITIVOS_INTERMEDIOS003,Utilidades.CAMPO_ID_DISPOSITIVO_INTERMEDIO+"=?",parametros);
            Toast.makeText(getApplicationContext(), "Se elimino el registro", Toast.LENGTH_SHORT).show();
            db.close();
        }

        public void actualizarPosicionDispositivo(View view){
            campoUbiGeoLatitud.setText(latitud1);
            campoUbiGeoLongitud.setText(longitud1);

        }













    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setDispositivosIntermediosModificacion(this);

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
        DispositivosIntermediosModificacion DispositivosIntermediosModificacion;

        public DispositivosIntermediosModificacion getDispositivosIntermediosModificacion() {
            return DispositivosIntermediosModificacion;
        }

        public void setDispositivosIntermediosModificacion(DispositivosIntermediosModificacion DispositivosIntermediosModificacion) {
            this.DispositivosIntermediosModificacion = DispositivosIntermediosModificacion;
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
