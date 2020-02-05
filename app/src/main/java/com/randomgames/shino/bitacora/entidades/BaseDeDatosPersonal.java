package com.randomgames.shino.bitacora.entidades;

import java.io.Serializable;

/**
 * Created by shino on 5/1/2018.
 */

public class BaseDeDatosPersonal implements Serializable{
    private Integer id;
    private String nombre;
    private String email;
    private String telefono;

    public BaseDeDatosPersonal(Integer id,String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public BaseDeDatosPersonal(){

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
