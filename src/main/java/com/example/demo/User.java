package com.example.demo;

public abstract class User{
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String cf;

    public User(String username, String password, String nome, String cognome, String cf){
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
    }

    public User() {

    }

    public String getUsername(){
        return this.username;
    }
    public Boolean checkPassword(String password){
        return (PwHashing.hashing(password, this.password.substring(32))==this.password);
    }
    public String getPassword(){
        return this.password;
    }
    public String getNome(){
        return this.nome;
    }
    public String getCognome(){
        return this.cognome;
    }
    public String getCf(){
        return this.cf;
    }
    public abstract boolean getTipo();
}