package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbManager {
    //utilizziamo sqlite come database, quindi non c'è bisogno di stringhe user e password per aprire la connessione al db
    /**
     * Get a connection to database
     * @return Connection object
    */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:C:/Users/ndebi/Desktop/UNIMI/sweng/SveDB.db"); //inserire il path file del proprio databa\se
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}