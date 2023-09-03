package com.example.demo;

public class ModUser extends User{

    public ModUser(String username, String password, String nome, String cognome, String cf) {
        super(username, password, nome, cognome, cf);
    }


    @Override
    public boolean getTipo(){
        return true;
    }
}