package com.example.demo;

import com.example.demo.DbManager;
import com.example.demo.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class CandidatoDao {
    public CandidatoDao(){
    }

    public Candidato retrieveCandidato(String cf){
        try (Connection connection = DbManager.getConnection();){
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM candidati WHERE cf=?");
            stmt.setString(1,cf);
            ResultSet r = stmt.executeQuery();
            if(r.next())
            {
                return new Candidato(r.getString(1), r.getString(2), r.getString(3), r.getString(4));
            } else {
                return null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public boolean createCandidato(Candidato candidato){
        try (Connection connection = DbManager.getConnection();){
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO candidati VALUES (?,?,?,?)");
            stmt.setString(1,candidato.getNome());
            stmt.setString(2,candidato.getCognome());
            stmt.setString(3,candidato.getCf());
            stmt.setString(4,candidato.getSesso());
            int i = stmt.executeUpdate();
            if (i==1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean deleteCandidato(String cf){
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM candidati WHERE cf=?");
            stmt.setString(1,cf);
            int i = stmt.executeUpdate();
            /*PreparedStatement stmt2 = connection.prepareStatement("DELETE FROM candidatiPartito WHERE partito=?");
            stmt2.setString(1,id);
            int i2 = stmt2.executeUpdate();*/
            if (i==1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public Candidato[] retrieveAll(){
        ArrayList<Candidato> candidati = new ArrayList<Candidato>();
        try(Connection connection = DbManager.getConnection()){
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM candidati");
            ResultSet r = stmt.executeQuery();
            while (r.next()){
                candidati.add(new Candidato(r.getString(1), r.getString(2), r.getString(3), r.getString(4)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return candidati.toArray(new Candidato[0]);
    }

    public String retrievePartitoId(String candidato){
        try (Connection connection = DbManager.getConnection();){
            PreparedStatement stmt = connection.prepareStatement("SELECT partito FROM candidatiPartito WHERE candidato=?");
            stmt.setString(1,candidato);
            ResultSet r = stmt.executeQuery();
            if(r.next())
            {
                return r.getString(1);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}