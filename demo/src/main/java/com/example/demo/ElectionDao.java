package com.example.demo;

import java.sql.*;
import java.util.ArrayList;

public class ElectionDao {
    public ElectionDao() {
    }

    public boolean closeElection(){
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE elections SET status = ? WHERE status = 1");
            stmt.setInt(1,0);
            int i = stmt.executeUpdate();
            if (i==1){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void wipeDatabase(){
        try(Connection connection = DbManager.getConnection()){
            connection.createStatement().executeUpdate("PRAGMA foreign_keys = ON");
            connection.createStatement().executeUpdate("DROP TABLE IF EXISTS urna");
            connection.createStatement().executeUpdate("DELETE FROM partiti");
            connection.createStatement().executeUpdate("DELETE FROM candidati");
            connection.createStatement().executeUpdate("UPDATE baseUsers SET voted=0");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Election retrieveElection(){

        try (Connection connection = DbManager.getConnection()){
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM elections WHERE status=?");
            stmt.setString(1,"1");
            ResultSet r = stmt.executeQuery();
            if(r.next())
            {
                return new Election(r.getString(1), r.getString(2), r.getString(3), r.getInt(4), r.getInt(5), r.getString(6));
            } else {
                return null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public int retrieveId(Election election) {
        try(Connection connection = DbManager.getConnection()){
            PreparedStatement stmt = connection.prepareStatement("SELECT rowid FROM elections");
            ResultSet r = stmt.executeQuery();
            if(r.next()){
                return r.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    public Boolean createElection(Election election) {
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO elections VALUES (?,?,?,?,?,?)");
            stmt.setString(1,election.getData());
            stmt.setString(2,election.getTipoVotazione());
            stmt.setString(3,election.getTipoVittoria());
            stmt.setInt(4,election.getStatus());
            stmt.setInt(5,election.getTwoPrefs());
            stmt.setString(6, election.getReferendumQuest());
            int i = stmt.executeUpdate();
            if (i!=1) {
                return false;
            }
            String sqlString = "";
            Statement urnaPrep = connection.createStatement();
            switch (election.getTipoVotazione()){
                case "voto categorico":
                case "voto categorico (partiti)":
                    sqlString = "CREATE TABLE \"urna\" (" +
                            "\"candidato_partito\" TEXT," +
                            "\"blank\" INT" +
                            ")";
                    break;
                case "voto categorico con pref.":
                    sqlString = "CREATE TABLE \"urna\" (" +
                            "\"flag\" INT," +
                            "\"partito\" TEXT," +
                            "\"candidato1\" TEXT," +
                            "\"candidato2\" TEXT," +
                            "\"blank\" INT" +
                            ")";
                    break;
                case "voto ordinale":
                case "voto ordinale (partiti)":
                    sqlString = "CREATE TABLE \"urna\" (" +
                            "\"id\" TEXT," +
                            "\"candidato_partito\" TEXT," +
                            "\"pos\" INT," +
                            "\"blank\" INT" +
                            ")";
                    break;
                case "referendum":
                    sqlString = "CREATE TABLE \"urna\" (" +
                            "\"voto\" TEXT," +
                            "\"blank\" INT" +
                            ")";
                    break;
            }
            int i2 = urnaPrep.executeUpdate(sqlString);
            if (i2==1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /*public int returnID(){
        Connection connection = DbManager.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT MAX(id) FROM elections");
            ResultSet r = stmt.executeQuery();

            if(r.next())
            {
                return r.getInt(1)+1;
            } else {
                return 0;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }*/
}
