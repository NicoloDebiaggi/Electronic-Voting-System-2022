package com.example.demo;

public interface UserDao {
    public User retrieveUser(String username);
    public boolean createUser(User user);

    void createUser(String diocane, String diocanePassword);
}