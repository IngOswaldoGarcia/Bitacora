package com.randomgames.shino.bitacora.utilidades;

/**
 * Created by shino on 4/25/2018.
 */

public class Utilidades {


    //Constantes campos tabla dispositivos intermedios
    public static final String TABLA_DISPOSITIVOS_INTERMEDIOS003="baseDeDatosDispositivosIntermedios003";
    public static final String CAMPO_ID_DISPOSITIVO_INTERMEDIO="idDispositivoIntermedio";
    public static final String CAMPO_TIPO_DISPOSITIVO="tipoDispositivo";
    public static final String CAMPO_NUMERO_SERIE="numeroSerie";
    public static final String CAMPO_MODELO="modelo";
    public static final String CAMPO_SISTEMAOPERATIVO="sistemaOperativo";
    public static final String CAMPO_ARCHIVO_CONFIGURACION="archivoDeConfiguracion";
    public static final String CAMPO_CREDENCIALES_ACCESO_USER="credencialesDeAccesoUser";
    public static final String CAMPO_CREDENCIALES_ACCESO_CONTRASEÑA="credencialesDeAccesoContraseña";
    public static final String CAMPO_UBICACION_GEOGRAFICA_LATITUD="ubicacionGeograficaLatitud";
    public static final String CAMPO_UBICACION_GEOGRAFICA_LONGITUD="ubicacionGeograficaLongitud";

    public static final String CREAR_TABLA_DISPOSITIVOS_INTERMEDIOS003 = "CREATE TABLE "
            + ""+TABLA_DISPOSITIVOS_INTERMEDIOS003+" ("
            +CAMPO_ID_DISPOSITIVO_INTERMEDIO+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_TIPO_DISPOSITIVO+" TEXT,"
            +CAMPO_NUMERO_SERIE+" TEXT,"
            +CAMPO_MODELO+" TEXT,"
            +CAMPO_SISTEMAOPERATIVO+" TEXT,"
            +CAMPO_ARCHIVO_CONFIGURACION+" TEXT,"
            +CAMPO_CREDENCIALES_ACCESO_USER+" TEXT,"
            +CAMPO_CREDENCIALES_ACCESO_CONTRASEÑA+" TEXT,"
            +CAMPO_UBICACION_GEOGRAFICA_LATITUD+" TEXT, "
            +CAMPO_UBICACION_GEOGRAFICA_LONGITUD+" TEXT)";

    //Constantes campos tabla dispositivos Finales
    public static final String TABLA_DISPOSITIVOS_FINALES001="baseDeDatosDispositivosFinales001";
    public static final String CAMPO_ID_DISPOSITIVO_FINAL="idDispositivoFinal";
    public static final String CAMPO_TIPO_DISPOSITIVO_FINAL="tipoDispositivoFinal";
    public static final String CAMPO_NUMERO_SERIE_FINAL="numeroSerieFinal";
    public static final String CAMPO_MODELO_FINAL="modeloFinal";
    public static final String CAMPO_SISTEMAOPERATIVO_FINAL="sistemaOperativoFinal";
    public static final String CAMPO_CREDENCIALES_ACCESO_USER_FINAL="credencialesDeAccesoUserFinal";
    public static final String CAMPO_CREDENCIALES_ACCESO_CONTRASEÑA_FINAL="credencialesDeAccesoContraseñaFinal";
    public static final String CAMPO_PROPIETARIO_FINAL="propietarioFinal";
    public static final String CAMPO_UBICACION_GEOGRAFICA_LATITUD_FINAL="ubicacionGeograficaLatitud";
    public static final String CAMPO_UBICACION_GEOGRAFICA_LONGITUD_FINAL="ubicacionGeograficaLongitudFinal";

    public static final String CREAR_TABLA_DISPOSITIVOS_FINALES001 = "CREATE TABLE "
            + ""+TABLA_DISPOSITIVOS_FINALES001+" ("
            +CAMPO_ID_DISPOSITIVO_FINAL+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_TIPO_DISPOSITIVO_FINAL+" " + "TEXT,"
            +CAMPO_NUMERO_SERIE_FINAL+" TEXT,"
            +CAMPO_MODELO_FINAL+" TEXT,"
            +CAMPO_SISTEMAOPERATIVO_FINAL+" TEXT,"
            +CAMPO_CREDENCIALES_ACCESO_USER_FINAL+" TEXT,"
            +CAMPO_CREDENCIALES_ACCESO_CONTRASEÑA_FINAL+" TEXT,"
            +CAMPO_PROPIETARIO_FINAL+" TEXT,"
            +CAMPO_UBICACION_GEOGRAFICA_LATITUD_FINAL+" TEXT, "
            +CAMPO_UBICACION_GEOGRAFICA_LONGITUD_FINAL+" TEXT)";

    //Constantes campos tabla conexiones
    public static final String TABLA_CONEXIONES001="baseDeDatosConexiones001";
    public static final String CAMPO_ID_CONEXIONES="idConexiones";
    public static final String CAMPO_CONEXION_ORIGEN="conexionOrigen";
    public static final String CAMPO_CONEXION_DESTINO="conexionDestino";
    public static final String CAMPO_MEDIO="medio";

    public static final String CREAR_TABLA_CONEXIONES001 = "CREATE TABLE "
            + ""+TABLA_CONEXIONES001+" ("
            +CAMPO_ID_CONEXIONES+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_CONEXION_ORIGEN+" " + "TEXT,"
            +CAMPO_CONEXION_DESTINO+" " + "TEXT,"
            +CAMPO_MEDIO+" TEXT)";

    //CONSTANTES campos tabla personal
    public static final String TABLA_PERSONAL001="baseDeDatosPersonal001";
    public static final String CAMPO_ID_PERSONAL="idPersonal";
    public static final String CAMPO_NOMBRE="campoNombre";
    public static final String CAMPO_EMAIL="campoEmail";
    public static final String CAMPO_TELEFONO="campoTelefono";


    public static final String CREAR_TABLA_PERSONAL001 = "CREATE TABLE "
            + ""+TABLA_PERSONAL001+" ("
            +CAMPO_ID_PERSONAL+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE+" " + "TEXT, "
            +CAMPO_EMAIL+" " + "TEXT,"
            +CAMPO_TELEFONO+" TEXT)";
}
