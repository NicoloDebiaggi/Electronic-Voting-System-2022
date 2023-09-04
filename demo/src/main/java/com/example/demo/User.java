package com.example.demo;

public abstract class User{
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String cf;

    public User(String cf, String password, String nome, String cognome){
        this.cf = cf;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
    }
    public String getUsername(){
        return this.cf;
    }
    public Boolean checkPassword(String password){
        String pwTrue = this.password;
        String salt = pwTrue.substring(pwTrue.length()-32);
        String pwGiven = (PwHashing.hashing(password, salt));

        return (pwTrue.equals(pwGiven));
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
    public abstract boolean getTipo();
}