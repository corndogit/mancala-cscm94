package com.swansea.mancala;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    static Connection connect = null;
    public static Connection dbConnection(){
        try{
            String url = "jdbc:mysql://localhost:3306/User";
            String username = "root";
            String pass = "Swapnil@123";
            connect = DriverManager.getConnection(url,username,pass);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return connect;
    }


}
