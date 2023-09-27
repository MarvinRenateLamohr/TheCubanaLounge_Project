package za.co.marvinlamohr.thecubanalounge;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public Connection database;

    public Connection getConnection(){
        String databaseName ="thecubanalounge_db";
        String databaseUser ="root";
        String databasePassword ="";
        String url ="jdbc:mysql://localhost:3306/"+databaseName+"?useSSL=false";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            database = DriverManager.getConnection(url,databaseUser,databasePassword);

        }catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
        return database;
    }

}
