package com.randomgames.shino.bitacora.entidades;

import java.io.Serializable;

/**
 * Created by shino on 5/1/2018.
 */

public class BaseDeDatosConexiones implements Serializable {
    private Integer id;
    private String conexionOrigen;
    private String conexionDestino;
    private String medio;


    public BaseDeDatosConexiones(Integer id, String conexionOrigen, String conexionDestino, String medio) {
        this.id = id;
        this.conexionOrigen = conexionOrigen;
        this.conexionDestino = conexionDestino;
        this.medio = medio;
    }

    public BaseDeDatosConexiones(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConexionOrigen() {
        return conexionOrigen;
    }

    public void setConexionOrigen(String conexionOrigen) {
        this.conexionOrigen = conexionOrigen;
    }

    public String getConexionDestino() {
        return conexionDestino;
    }

    public void setConexionDestino(String conexionDestino) {
        this.conexionDestino = conexionDestino;
    }

    public String getMedio() {
        return medio;
    }

    public void setMedio(String medio) {
        this.medio = medio;
    }
}
