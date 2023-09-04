package com.example.demo;

public class SchedaOrdinale extends Scheda {
    private String[] candidati_partiti;
    private int blank;

    public SchedaOrdinale(String[] candidati_partiti, int blank){
        this.candidati_partiti = candidati_partiti;
        this.blank = blank;
    }

    public String[] getCandidati_partiti(){return this.candidati_partiti;}
    public int getBlank(){return this.blank;}

}
