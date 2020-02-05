package com.randomgames.shino.bitacora.entidades;

/**
 * Created by shino on 4/25/2018.
 */

public class BaseDeDatosDispositivosIntermedios {

    private Integer id;
    private String nombre;
    private String numeroSerie;
    private String modelo;
    private String ubicacionGeograficaLatitud;
    private String ubicacionGeograficaLongitud;
    private String sistemaOperativo;
    private String archivoDeConfiguracion;
    private String credencialesDeAccesoUser;
    private String credencialesDeAccesoContraseña;

    public BaseDeDatosDispositivosIntermedios
            (Integer id, String nombre, String numeroSerie, String modelo,
             String sistemaOperativo, String archivoDeConfiguracion,
             String credencialesDeAccesoUser, String credencialesDeAccesoContraseña,
             String ubicacionGeograficaLatitud,String ubicacionGeograficaLongitud) {

        this.id = id;
        this.nombre = nombre;
        this.numeroSerie = numeroSerie;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.archivoDeConfiguracion = archivoDeConfiguracion;
        this.credencialesDeAccesoUser = credencialesDeAccesoUser;
        this.credencialesDeAccesoContraseña = credencialesDeAccesoContraseña;
        this.ubicacionGeograficaLatitud = ubicacionGeograficaLatitud;
        this.ubicacionGeograficaLongitud = ubicacionGeograficaLongitud;

    }

    public BaseDeDatosDispositivosIntermedios(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getUbicacionGeograficaLongitud() {
        return ubicacionGeograficaLongitud;
    }

    public void setUbicacionGeograficaLongitud(String ubicacionGeograficaLongitud) {
        this.ubicacionGeograficaLongitud = ubicacionGeograficaLongitud;
    }
    public String getUbicacionGeograficaLatitud() {
        return ubicacionGeograficaLatitud;
    }

    public void setUbicacionGeograficaLatitud(String ubicacionGeograficaLatitud) {
        this.ubicacionGeograficaLatitud = ubicacionGeograficaLatitud;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getArchivoDeConfiguracion() {
        return archivoDeConfiguracion;
    }

    public void setArchivoDeConfiguracion(String archivoDeConfiguracion) {
        this.archivoDeConfiguracion = archivoDeConfiguracion;
    }

    public String getCredencialesDeAccesoUser() {
        return credencialesDeAccesoUser;
    }

    public void setCredencialesDeAccesoUser(String credencialesDeAccesoUser) {
        this.credencialesDeAccesoUser = credencialesDeAccesoUser;
    }

    public String getCredencialesDeAccesoContraseña() {
        return credencialesDeAccesoContraseña;
    }

    public void setCredencialesDeAccesoContraseña(String credencialesDeAccesoContraseña) {
        this.credencialesDeAccesoContraseña = credencialesDeAccesoContraseña;
    }
}
