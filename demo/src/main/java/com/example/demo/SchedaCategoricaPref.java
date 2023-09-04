package com.example.demo;

public class SchedaCategoricaPref extends Scheda {

    private int flag;
    private String candidato1;
    private String candidato2;
    private String partito;
    private int blank;

    public SchedaCategoricaPref(int flag, String partito, String candidato1, String candidato2, int blank){
        this.flag = flag;
        this.candidato1 = candidato1;
        this.candidato2 = candidato2;
        this.partito = partito;
        this.blank = blank;
    }

    public int getFlag() { return this.flag; };
    public String getCandidato1(){return this.candidato1;}
    public String getCandidato2(){return this.candidato2;}
    public String getPartito(){return this.partito;}
    public int getBlank(){return this.blank;}

}
