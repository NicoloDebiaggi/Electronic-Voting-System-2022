package com.example.demo;

public class SchedaReferendum extends Scheda {
    private String voto;
    private int blank;

    public SchedaReferendum(String voto, int blank){
        this.voto = voto;
        this.blank = blank;
    }

    public String getVoto(){return this.voto;}
    public int getBlank(){return this.blank;}

}
