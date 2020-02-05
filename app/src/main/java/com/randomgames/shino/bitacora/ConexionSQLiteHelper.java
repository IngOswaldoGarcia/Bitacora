package com.randomgames.shino.bitacora;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.randomgames.shino.bitacora.utilidades.Utilidades;

/**
 * Created by shino on 4/25/2018.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper{

    //final String CREAR_TABLA_DISPOSITIVOS_INTERMEDIOS002="CREATE TABLE dispositivosIntermedios002 (nombre TEXT, numeroSerie TEXT, modelo TEXT, sistemaOperativo TEXT, archivoDeConfiguracion TEXT, credencialesDeAccesoUser TEXT,credencialesDeAccesoContraseña TEXT, ubicacionGeograficaLatitud, ubicacionGeograficaLongitud TEXT TEXT)";

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_DISPOSITIVOS_INTERMEDIOS003);
        db.execSQL(Utilidades.CREAR_TABLA_DISPOSITIVOS_FINALES001);
        db.execSQL(Utilidades.CREAR_TABLA_CONEXIONES001);
        db.execSQL(Utilidades.CREAR_TABLA_PERSONAL001);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS dispositivosIntermedios003");
        db.execSQL("DROP TABLE IF EXISTS dispositivosFinales001");
        db.execSQL("DROP TABLE IF EXISTS conexiones001");
        db.execSQL("DROP TABLE IF EXISTS personal001");
        onCreate(db);
    }
}
