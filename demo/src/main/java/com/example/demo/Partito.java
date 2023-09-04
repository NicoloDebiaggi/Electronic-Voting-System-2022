package com.example.demo;

import java.util.ArrayList;

public class Partito extends CandidatoGen{
    private String nomeId;
    private String displayName;
    private ArrayList<String> candidatiCF;

    public Partito(String nomeId, String displayName, ArrayList<String> candidatiCF){
        this.nomeId = nomeId;
        this.displayName = displayName;
        if (candidatiCF == null) {
            this.candidatiCF = new ArrayList<String>();
        } else {
            this.candidatiCF = candidatiCF;
        }
    }

    public void aggiungiCandidato(String candidatoCF){
        this.candidatiCF.add(candidatoCF);
    }

    public String getNomeId(){
        return nomeId;
    }

    public String getDisplayName(){
        return displayName;
    }
    
    public String[] getCandidati(){
        return candidatiCF.toArray(new String[0]);
    }
}