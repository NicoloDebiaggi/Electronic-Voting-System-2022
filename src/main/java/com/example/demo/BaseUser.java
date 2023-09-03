package com.example.demo;

public class BaseUser extends User{

    public BaseUser(String string1, String string2, String string3, String string4, String string5) {
        super(string1,string2,string3,string4,string5);
    }

    @Override
    public boolean getTipo(){
        return false;
    }
}