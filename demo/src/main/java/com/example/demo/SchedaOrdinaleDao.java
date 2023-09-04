package com.example.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class SchedaOrdinaleDao {
    public SchedaOrdinaleDao(){
    }

    public SchedaOrdinale[] retrieveSchede(){
        ArrayList<SchedaOrdinale> resultList = new ArrayList<SchedaOrdinale>();
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement pre = connection.prepareStatement("SELECT max(id), min(id) FROM urna");
            ResultSet rPre = pre.executeQuery();
            int maxId = rPre.getInt(1);
            int minId = rPre.getInt(2);
            for (int i=minId; i<=maxId; i++) {
                PreparedStatement stmt = connection.prepareStatement("SELECT candidato_partito, blank FROM urna WHERE id = ? ORDER BY pos ASC");
                stmt.setString(1, String.valueOf(i));
                ResultSet r = stmt.executeQuery();
                ArrayList<String> candidatiVol = new ArrayList<String>();
                int blank = 0;
                while (r.next()){
                    candidatiVol.add(r.getString(1));
                    blank=(r.getInt(2));
                }
                System.out.println("-"+candidatiVol);
                resultList.add(new SchedaOrdinale(candidatiVol.toArray(new String[candidatiVol.size()]), blank));
            }
            System.out.println("-"+resultList);
            return resultList.toArray(new SchedaOrdinale[resultList.size()]);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public boolean createScheda(SchedaOrdinale scheda){
        String[] candidati_partiti = scheda.getCandidati_partiti();
        if(candidati_partiti.length == 0&&scheda.getBlank()==0) {
            return false;
        }
        int blank = scheda.getBlank();
        try(Connection connection = DbManager.getConnection()) {
            PreparedStatement pre = connection.prepareStatement("SELECT MAX(id) FROM urna");
            ResultSet r2 = pre.executeQuery();
            int id = r2.getInt(1)+1;
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO urna VALUES (?,?,1,?)");
            stmt.setInt(1,id);
            stmt.setString(2,candidati_partiti[0]);
            stmt.setInt(3,blank);
            int i = stmt.executeUpdate();
            if (i!=1){return false;}
            for (int c = 1; c<candidati_partiti.length; c++){
                PreparedStatement stmt2 = connection.prepareStatement("INSERT INTO urna VALUES (?,?,?,?)");
                stmt2.setInt(1, id);
                stmt2.setString(2, candidati_partiti[c]);
                stmt2.setInt(3,c+1);
                stmt2.setInt(4,blank);
                stmt2.executeUpdate();
            }
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}