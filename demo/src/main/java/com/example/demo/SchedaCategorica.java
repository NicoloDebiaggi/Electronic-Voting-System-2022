package com.example.demo;

public class SchedaCategorica extends Scheda {
    private String candidato_partito;
    private int blank;

    public SchedaCategorica(String candidato_partito, int blank){
        this.blank = blank;
        this.candidato_partito = candidato_partito;
    }

    public String getCandidato_partito(){return this.candidato_partito;}
    public int getBlank(){return this.blank;}

}
