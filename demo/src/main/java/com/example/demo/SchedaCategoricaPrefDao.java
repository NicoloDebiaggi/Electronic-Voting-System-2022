package com.example.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class SchedaCategoricaPrefDao {
    public SchedaCategoricaPrefDao(){
    }

    public SchedaCategoricaPref[] retrieveSchede(){
        ArrayList<SchedaCategoricaPref> resultList = new ArrayList<SchedaCategoricaPref>();
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM urna");
            ResultSet r = stmt.executeQuery();
            while(r.next())
            {
                resultList.add(new SchedaCategoricaPref(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getInt(5)));
            }
            SchedaCategoricaPref[] resultArray = resultList.toArray(new SchedaCategoricaPref[resultList.size()]);
            return resultArray;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public boolean createScheda(SchedaCategoricaPref scheda){
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO urna VALUES (?,?,?,?,?)");
            stmt.setInt(1,scheda.getFlag());
            stmt.setString(2,scheda.getPartito());
            stmt.setString(3,scheda.getCandidato1());
            stmt.setString(4,scheda.getCandidato2());
            stmt.setInt(5,scheda.getBlank());
            int i = stmt.executeUpdate();
            if (i==1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}