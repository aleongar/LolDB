package com.example.lol.models;

public class ChampionModel {
    private String name;
    private String[] habilidades;
    private String dagno;

    public ChampionModel(String name, String habilidades, String dano){
        this.name = name;
        this.habilidades = habilidades.split(", ");
        this.dagno = dano;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String[] habilidades) {
        this.habilidades = habilidades;
    }

    public String getDagno() {
        return dagno;
    }

    public void setDano(String dano) {
        this.dagno = dano;
    }
}
