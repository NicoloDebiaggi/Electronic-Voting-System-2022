package com.example.demo;

public class Election {

    private int id;
    private String data;
    private String tipoVotazione;
    private String tipoVittoria;
    private int status;
    private int twoPrefs;
    private String referendumQuest;

    public Election (String data, String tipoVotazione, String tipoVittoria, int status, int twoPrefs, String referendumQuest){
        this.data = data;
        this.tipoVotazione = tipoVotazione;
        this.tipoVittoria = tipoVittoria;
        this.status = status;
        this.twoPrefs = twoPrefs;
        this.referendumQuest = referendumQuest;
    }

    public int getId(){
        return this.id;
    }
    public String getData(){
        return this.data;
    }
    public String getTipoVotazione(){
        return this.tipoVotazione;
    }
    public String getTipoVittoria(){
        return this.tipoVittoria;
    }
    public int getStatus(){
        return this.status;
    }
    public int getTwoPrefs() { return this.twoPrefs; }
    public String getReferendumQuest() { return this.referendumQuest; }
}
