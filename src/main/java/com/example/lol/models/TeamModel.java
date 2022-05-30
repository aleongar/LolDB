package com.example.lol.models;

public class TeamModel {
    private String id;
    private String nombre;
    private String nacion;

    public TeamModel(String id, String nombre, String nacion) {
        this.id = id;
        this.nombre = nombre;
        this.nacion = nacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacion() {
        return nacion;
    }

    public void setNacion(String nacion) {
        this.nacion = nacion;
    }
}
