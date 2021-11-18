/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.CityModel;
import Model.SuperModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import utils.ConnectionDB;

/**
 *
 * @author SANTIAGO SIERRA
 */
public class CityDAO {
    private Connection conn = null;
    
    
    public void insertCity(CityModel city) {
        
        try{

            conn = ConnectionDB.getConnection();

            
            String sql = "insert into city(city_name) values (?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,city.getCityName());
            
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0){
                JOptionPane.showMessageDialog(null,"El registro fue agregado exitosamente");
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        } finally {
            try{
                conn.close();
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
            }
        }
    }
    
    
     public ArrayList<SuperModel> getAllCities(){
        ArrayList<SuperModel> cities = new ArrayList();
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "select * from city;";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()){
                SuperModel city = new CityModel(result.getString(1));
                cities.add(city);
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        } finally {
            try{
                conn.close();
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
            }
        }
        
        return cities;
    }
    

     public ArrayList<SuperModel> getCitiesByFilter(String cityName){
        ArrayList<SuperModel> cities = new ArrayList();
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "select * from city where city_name like ?";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%"+cityName+"%"); 
            
            System.out.println(statement.toString());
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                SuperModel city = new CityModel(result.getString(1));
                cities.add(city);
            }
            
        }
        
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        } finally {
            try{
                conn.close();
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
            }
        }
        return cities;
    }
     
     
     
    public void update(String newCityName, String CurrentcityName){
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "update city set city_name=? where city_name =?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, newCityName);
            statement.setString(2, CurrentcityName);
            
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                JOptionPane.showMessageDialog(null,"El registro " + CurrentcityName + " fue modificado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null,"El registro no existe");
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        } finally {
            try{
                conn.close();
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
            }
        }
        
    }
    
    public void delete(String cityName){
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "delete from city where city_name like ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cityName);
            
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                JOptionPane.showMessageDialog(null,"El registro fue eliminado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null,"El registro no existe");
            }
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
        } finally {
            try{
                conn.close();
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError : " + ex.getMessage());
            }
        }
    }
}
