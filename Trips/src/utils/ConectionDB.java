/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author SANTIAGO SIERRA
 */
public class ConectionDB {
    
    public static Connection getConnection() throws IOException, ParseException{
        JSONParser parser = new JSONParser();
        Connection conn = null;
        
        
        try {
            String credentials_path = System.getProperty("user.dir") + "/src/utils/db_credentials.json";
            JSONObject jsonObject = (JSONObject)parser.parse(new FileReader(credentials_path));
            
            String host     = (String)jsonObject.get("db_ip");
            String port     = (String)jsonObject.get("db_port");
            String username = (String)jsonObject.get("db_user");
            String password = (String)jsonObject.get("db_password");
            String dbURL = "jdbc:mysql://"+host+":"+port+"/trip";
            
            conn = DriverManager.getConnection(dbURL,username,password);
            
            if( conn != null ) 
                System.out.println ( "Conectado" );
        }
        catch(SQLException | FileNotFoundException ex){
            ex.printStackTrace();
        } 
        catch(IOException | ParseException ex){
            ex.printStackTrace();
        }
        
        return conn;
    }
    
    public static void main(String[] args) throws IOException, ParseException{
        getConnection();
    }
    
}
