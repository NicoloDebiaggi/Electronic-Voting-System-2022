package com.example.demo;

public class BaseUser extends User{

    private int voted;
    private static BaseUser baseUserInstance;

    public BaseUser(String string1, String string2, String string3, String string4, int voted) {
        super(string1,string2,string3,string4);
        this.voted = voted;
    }

    //Singleton
    public static void setInstance(BaseUser user){
        baseUserInstance = user;
    }
    public static BaseUser getInstance(){
        if(baseUserInstance == null){
            return null;
        }
        return baseUserInstance;
    }

    @Override
    public boolean getTipo(){
        return false;
    }
    public int getVoted() { return voted; }
}