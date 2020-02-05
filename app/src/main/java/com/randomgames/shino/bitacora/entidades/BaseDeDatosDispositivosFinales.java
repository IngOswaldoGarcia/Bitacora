package com.randomgames.shino.bitacora.entidades;

import java.io.Serializable;

/**
 * Created by shino on 4/30/2018.
 */

public class BaseDeDatosDispositivosFinales implements Serializable {

    private Integer id;
    private String tipo;
    private String numeroSerie;
    private String modelo;
    private String sistemaOperativo;
    private String credencialesDeAccesoUser;
    private String credencialesDeAccesoContraseña;
    private String Propietario;
    private String ubicacionGeograficaLatitud;
    private String ubicacionGeograficaLongitud;


    public BaseDeDatosDispositivosFinales(Integer id,
                                          String tipo,
                                          String numeroSerie,
                                          String modelo,
                                          String sistemaOperativo,
                                          String credencialesDeAccesoUser,
                                          String credencialesDeAccesoContraseña,
                                          String propietario,
                                          String ubicacionGeograficaLatitud,
                                          String ubicacionGeograficaLongitud) {
        this.id = id;
        this.tipo = tipo;
        this.numeroSerie = numeroSerie;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.credencialesDeAccesoUser = credencialesDeAccesoUser;
        this.credencialesDeAccesoContraseña = credencialesDeAccesoContraseña;
        Propietario = propietario;
        this.ubicacionGeograficaLatitud = ubicacionGeograficaLatitud;
        this.ubicacionGeograficaLongitud = ubicacionGeograficaLongitud;
    }

    public BaseDeDatosDispositivosFinales(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
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

    public String getPropietario() {
        return Propietario;
    }

    public void setPropietario(String propietario) {
        Propietario = propietario;
    }

    public String getUbicacionGeograficaLatitud() {
        return ubicacionGeograficaLatitud;
    }

    public void setUbicacionGeograficaLatitud(String ubicacionGeograficaLatitud) {
        this.ubicacionGeograficaLatitud = ubicacionGeograficaLatitud;
    }

    public String getUbicacionGeograficaLongitud() {
        return ubicacionGeograficaLongitud;
    }

    public void setUbicacionGeograficaLongitud(String ubicacionGeograficaLongitud) {
        this.ubicacionGeograficaLongitud = ubicacionGeograficaLongitud;
    }
}
