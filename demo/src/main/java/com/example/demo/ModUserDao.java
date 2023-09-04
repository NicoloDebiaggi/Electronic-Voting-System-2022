package com.example.demo;

import com.example.demo.DbManager;
import com.example.demo.ModUser;
import com.example.demo.User;
import com.example.demo.UserDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class ModUserDao{
    public ModUserDao() {
    }
    public ModUser retrieveUser(String username) {
        try (Connection connection = DbManager.getConnection();) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM modUsers WHERE username=?");
            stmt.setString(1, username);
            ResultSet r = stmt.executeQuery();

            if (r.next()) {
                return new ModUser(r.getString(1), r.getString(2), r.getString(3), r.getString(4));
            } else {
                return null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public boolean createUser(User user) {
        try (Connection connection = DbManager.getConnection();) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO modUsers VALUES (?,?,?,?)");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getNome());
            stmt.setString(4, user.getCognome());
            int i = stmt.executeUpdate();
            if (i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
