package com.example.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class ModUserDao implements UserDao {
    public ModUserDao(){
    }

    @Override
    public User retrieveUser(String username){
        Connection connection = DbManager.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT password FROM baseUsers WHERE username=?");
            stmt.setString(1,username);
            ResultSet r = stmt.executeQuery();

            if(r.next())
            {
                return new ModUser(r.getString(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5));
            } else {
                return null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    return null;
    }

    @Override
    public boolean createUser(User user){
        Connection connection = DbManager.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO modUsers VALUES (?,?,?,?,?)");
            stmt.setString(1,user.getUsername());
            stmt.setString(2,user.getPassword());
            stmt.setString(3,user.getNome());
            stmt.setString(4,user.getCognome());
            stmt.setString(5,user.getCf());
            int i = stmt.executeUpdate();
            if (i==1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        return false;
    }

    @Override
    public void createUser(String diocane, String diocanePassword) {

    }
}