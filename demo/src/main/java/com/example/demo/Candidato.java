package com.example.demo;

public class Candidato extends CandidatoGen{
    private String nome;
    private String cognome;
    private String cf;
    private String sesso;

    public Candidato(String nome, String cognome, String cf, String sesso){
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.sesso = sesso;
    }

    public String getNome(){
        return nome;
    }

    public String getCognome(){
        return cognome;
    }

    public String getCf(){
        return cf;
    }
    public String getSesso() { return sesso; }
}