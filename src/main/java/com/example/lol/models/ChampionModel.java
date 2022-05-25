package com.example.lol.models;

import java.util.ArrayList;

public class ChampionModel {
    private String name;
    private String[] habilidades;
    private String dano;

    public ChampionModel(String name, String habilidades, String dano){
        this.name = name;
        this.habilidades = habilidades.split(", ");
        this.dano = dano;
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

    public String getDano() {
        return dano;
    }

    public void setDano(String dano) {
        this.dano = dano;
    }
}
