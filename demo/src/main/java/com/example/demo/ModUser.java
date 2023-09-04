package com.example.demo;

public class ModUser extends User{

    public ModUser(String string1, String string2, String string3, String string4) {
        super(string1,string2,string3,string4);
    }


    @Override
    public boolean getTipo(){
        return true;
    }
}