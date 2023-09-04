package com.example.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class BaseUserDao{
    public BaseUserDao(){
    }

    public BaseUser retrieveUser(String username){
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM baseUsers WHERE username=?");
            stmt.setString(1,username);
            ResultSet r = stmt.executeQuery();
            if(r.next())
            {
                return new BaseUser(r.getString(1), r.getString(2), r.getString(3), r.getString(4), r.getInt(5));
            } else {
                return null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public boolean createUser(BaseUser user){
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO baseUsers VALUES (?,?,?,?,?)");
            stmt.setString(1,user.getUsername());
            stmt.setString(2,user.getPassword());
            stmt.setString(3,user.getNome());
            stmt.setString(4,user.getCognome());
            stmt.setInt(5,user.getVoted());
            int i = stmt.executeUpdate();
            if (i==1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public void setVoted(String username){
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE baseUsers SET voted=1 WHERE username = ?");
            stmt.setString(1,username);
            int i = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        BaseUser.setInstance(retrieveUser(username));
    }
}