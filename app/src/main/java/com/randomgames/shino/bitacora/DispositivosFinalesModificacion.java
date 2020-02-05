package com.randomgames.shino.bitacora;

import android.*;
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

public class DispositivosFinalesModificacion extends AppCompatActivity {

    EditText campoID, campoNombre, campoNumSerie, campoModelo, campoUbiGeoLatitud, campoUbiGeoLongitud, campoSisOpera, campoPropietario, campoCredenAccesoUser, campoCredenAccesoContraseña;
    Button btnEliminar, btnActualizar,btnRegistrarDisp;
    String latitud1="", longitud1="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos_finales_modificacion);

        campoID = (EditText) findViewById(R.id.idIdDipositivoFinalModificacion);
        campoNombre = (EditText) findViewById(R.id.idTipoDispFinal2);
        campoNumSerie = (EditText) findViewById(R.id.idNumSerieFinal2);
        campoModelo = (EditText) findViewById(R.id.idModeloFinal2);
        campoSisOpera = (EditText) findViewById(R.id.idSisOperaFinal2);
        campoPropietario = (EditText) findViewById(R.id.idPropietarioFinal2);
        campoCredenAccesoUser = (EditText) findViewById(R.id.idCredenAccesoFinal2);
        campoCredenAccesoContraseña = (EditText) findViewById(R.id.idCredenAccesoContraseñaFinal2);
        campoUbiGeoLatitud = (EditText) findViewById(R.id.idUbicGeoFinal2);
        campoUbiGeoLongitud = (EditText) findViewById(R.id.idUbicGeoLongFinal2);

        btnEliminar = (Button) findViewById(R.id.idButtonToEliminateDeviceFinal2);
        btnActualizar = (Button) findViewById(R.id.idButtonToUpdateDiviceFinal2);

        Bundle bundle2 = getIntent().getExtras();

        if (bundle2 != null) {

            String id = bundle2.getString("id");
            String nombre = bundle2.getString("nombre");
            String numSerie = bundle2.getString("numSerie");
            String modelo = bundle2.getString("modelo");
            String sisOperativo = bundle2.getString("sisOperativo");
            String user = bundle2.getString("user");
            String contraseña = bundle2.getString("contraseña");
            String propietario = bundle2.getString("propietario");
            String latitudString = bundle2.getString("latitudString");
            String longitudString = bundle2.getString("longitudString");

            campoID.setText(id);
            campoNombre.setText(nombre);
            campoNumSerie.setText(numSerie);
            campoModelo.setText(modelo);
            campoSisOpera.setText(sisOperativo);
            campoCredenAccesoUser.setText(user);
            campoCredenAccesoContraseña.setText(contraseña);
            campoPropietario.setText(propietario);
            campoUbiGeoLatitud.setText(latitudString);
            campoUbiGeoLongitud.setText(longitudString);
        }

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }

    }

    ConexionSQLiteHelper connDipsInterFinal=new ConexionSQLiteHelper(this,"bd_dispositivosFinales001",null,1);

    public void actualizarDispositivo(View view){

        SQLiteDatabase db=connDipsInterFinal.getWritableDatabase();
        String[]parametros={campoID.getText().toString()};
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_TIPO_DISPOSITIVO_FINAL,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_NUMERO_SERIE_FINAL,campoNumSerie.getText().toString());
        values.put(Utilidades.CAMPO_MODELO_FINAL,campoModelo.getText().toString());
        values.put(Utilidades.CAMPO_SISTEMAOPERATIVO_FINAL,campoSisOpera.getText().toString());
        values.put(Utilidades.CAMPO_CREDENCIALES_ACCESO_USER_FINAL,campoCredenAccesoUser.getText().toString());
        values.put(Utilidades.CAMPO_CREDENCIALES_ACCESO_CONTRASEÑA_FINAL,campoCredenAccesoContraseña.getText().toString());
        values.put(Utilidades.CAMPO_PROPIETARIO_FINAL,campoPropietario.getText().toString());
        values.put(Utilidades.CAMPO_UBICACION_GEOGRAFICA_LATITUD_FINAL,campoUbiGeoLatitud.getText().toString());
        values.put(Utilidades.CAMPO_UBICACION_GEOGRAFICA_LONGITUD_FINAL,campoUbiGeoLongitud.getText().toString());

        db.update(Utilidades.TABLA_DISPOSITIVOS_FINALES001,values,Utilidades.CAMPO_ID_DISPOSITIVO_FINAL+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Se Actualizo el Dispositivo", Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void eliminarDispositivo(View view){
        SQLiteDatabase db=connDipsInterFinal.getWritableDatabase();
        String[]parametros={campoID.getText().toString()};
        db.delete(Utilidades.TABLA_DISPOSITIVOS_FINALES001,Utilidades.CAMPO_ID_DISPOSITIVO_FINAL+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Se elimino el registro", Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void actualizarPosicionDispositivo(View view){
        campoUbiGeoLatitud.setText(latitud1);
        campoUbiGeoLongitud.setText(longitud1);

    }













    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        DispositivosFinalesModificacion.Localizacion Local = new DispositivosFinalesModificacion.Localizacion();
        Local.setDispositivosFinalesModificacion(this);

        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
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
        DispositivosFinalesModificacion DispositivosFinalesModificacion;

        public DispositivosFinalesModificacion getDispositivosFinalesModificacion() {
            return DispositivosFinalesModificacion;
        }

        public void setDispositivosFinalesModificacion(DispositivosFinalesModificacion DispositivosFinalesModificacion) {
            this.DispositivosFinalesModificacion = DispositivosFinalesModificacion;
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
