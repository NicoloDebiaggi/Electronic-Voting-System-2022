package com.example.demo;

import java.sql.*;
import java.util.*;

public class PartitoDao {
    public PartitoDao(){
    }

    public boolean aggiungiCandidato(String candidatoCF, String nomeId){
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO candidatiPartito VALUES (?,?)");
            stmt.setString(1,nomeId);
            stmt.setString(2,candidatoCF);
            int i = stmt.executeUpdate();
            if (i==1){return true;}
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean rimuoviCandidato(String candidatoCF, String nomeId){
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM candidatiPartito WHERE partito=? AND candidato=?");
            stmt.setString(1,nomeId);
            stmt.setString(2,candidatoCF);
            int i = stmt.executeUpdate();
            if (i==1){return true;}
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Partito[] retrieveAll(){
        ArrayList<String> ids = new ArrayList<String>();
        ArrayList<Partito> partiti = new ArrayList<Partito>();
        try(Connection connection = DbManager.getConnection()){
            PreparedStatement stmt = connection.prepareStatement("SELECT nomeId FROM partiti");
            ResultSet r = stmt.executeQuery();
            while (r.next()){
                ids.add(r.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        for (String id : ids){
            partiti.add(retrievePartito(id));
        }
        return partiti.toArray(new Partito[0]);
    }

    public Partito retrievePartito(String nomeId){
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM partiti WHERE nomeId=?");
            stmt.setString(1,nomeId);
            ResultSet r = stmt.executeQuery();

            if(r.next())
            {
                String displayName = r.getString(2);
                PreparedStatement stmt2 = connection.prepareStatement("SELECT candidato FROM candidatiPartito WHERE partito=?");
                stmt2.setString(1,nomeId);
                r = stmt2.executeQuery();
                ArrayList<String> candidati = new ArrayList<String>();
                while (r.next()){
                    candidati.add(r.getString(1));
                }
                return new Partito(nomeId, displayName, candidati);
            }
            return null;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public boolean createPartito(Partito partito){
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO partiti VALUES (?,?)");
            stmt.setString(1,partito.getNomeId());
            stmt.setString(2,partito.getDisplayName());
            int i = stmt.executeUpdate();
            for (i=0; i<partito.getCandidati().length-1; i++){
                PreparedStatement stmt2 = connection.prepareStatement("INSERT INTO candidatiPartito VALUES (?,?)");
                stmt2.setString(1, partito.getNomeId());
                stmt2.setString(2, partito.getCandidati()[i]);
                int o = stmt.executeUpdate();
            }
            if (i==1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean deletePartito(String id){
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM partiti WHERE nomeId=?");
            stmt.setString(1,id);
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
}