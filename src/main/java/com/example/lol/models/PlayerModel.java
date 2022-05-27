package com.example.lol.models;

public class PlayerModel{
    private String version;
    private String username;
    private int mastery;
    private String bestChamp;
    private String team;
    private String name;
    private String surname;
    private String nationality;

    public PlayerModel(String version, int mastery, String username, String bestChamp, String team, String name, String surname, String nationality) {
        this.version = version;
        this.mastery = mastery;
        this.username = username;
        this.bestChamp = bestChamp;
        this.team = team;
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMastery() {
        return mastery;
    }

    public void setMastery(int mastery) {
        this.mastery = mastery;
    }

    public String getBestChamp() {
        return bestChamp;
    }

    public void setBestChamp(String bestChamp) {
        this.bestChamp = bestChamp;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
