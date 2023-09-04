package com.example.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class SchedaReferendumDao {
    public SchedaReferendumDao(){
    }

    public SchedaReferendum[] retrieveSchede(){
        ArrayList<SchedaReferendum> resultList = new ArrayList<SchedaReferendum>();
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM urna");
            ResultSet r = stmt.executeQuery();
            while(r.next())
            {
                resultList.add(new SchedaReferendum(r.getString(1), r.getInt(2)));
            }
            SchedaReferendum[] resultArray = resultList.toArray(new SchedaReferendum[0]);
            return resultArray;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public boolean createScheda(SchedaReferendum scheda){
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO urna VALUES (?,?)");
            stmt.setString(1,scheda.getVoto());
            stmt.setInt(2,scheda.getBlank());
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