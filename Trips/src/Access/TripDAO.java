/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.SuperModel;
import Model.TripModel;
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
public class TripDAO {
    private Connection conn = null;
    
    
    public void insertTrip(TripModel trip) {
        
        try{

            conn = ConnectionDB.getConnection();
            
            String sql = "insert into trip(trip_date, price, origin_city_fk, destiny_city_fk, employee_num_fk, bus_id_fk) values (?,?,?,?,?,?);";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            
            
                statement.setDate(1, trip.getTripDate());
                statement.setInt(2, trip.getPrice());
                statement.setString(3, trip.getOriginCityFk());
                statement.setString(4, trip.getOriginCityFk());
                statement.setInt(5, trip.getEmployeeNumFk());
                statement.setInt(6, trip.getBusIDFk());
            
            
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
    
    
     public ArrayList<SuperModel> getAllTrips(){
        ArrayList<SuperModel> tripsList = new ArrayList();
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "select * from trip;";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()){
                SuperModel trip = new TripModel(result.getInt(1), result.getDate(2), result.getInt(3), result.getString(4), result.getString(5), result.getInt(6), result.getInt(7));
                tripsList.add(trip);
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
        
        return tripsList;
    }
    
    
     public ArrayList<SuperModel> getTripsByFilter(TripModel trip){

        ArrayList<SuperModel> tripsList = new ArrayList();
        
        //        ArrayList<String> selectedFilters = new ArrayList();
        //Una posibilidad es recorrer el objeto y si el valor es diferente a -1 u "ALL" utilizo un ArrayList para guardar las concatenaciones de la 
        //sentecia sql combinado con if statements y luego recorrer ese array y con if statements para el tipo de dato de la injección ir
        //ingresandolos con un for que i sea 1,2,3, y los datos que hayan
        
        //Otra opción es no utilizar el data injection suponiendo que todo se convierte en cadenas al ponerlo entre ""+VALOR+ "" e 
        //ir concatenando según los valores sean diferentes de -1 y ALL
        

        
        try{
            
            conn = ConnectionDB.getConnection();
           
             String sql = "select * from trip where trip_id like ?";
        
            if(trip.getTripID() != -1){
                sql += " and trip_id ="+trip.getTripID()+"";
            }

            //Pendiente de ver que valor se obtiene de la fecha cuando se deja vacía para usarla en el condicional
    //        if(trip.getTripDate() != "ALL"){
    //            
    //        }

            if(trip.getPrice() != -1){
                sql += " and price ="+trip.getPrice()+"";
            }


            if(!trip.getOriginCityFk().equals("ALL")){
                sql += " and origin_city_fk ="+trip.getOriginCityFk()+"";
            }

            if(!trip.getDestinyCityFk().equals("ALL")){
                sql += " and destiny_city_fk ="+trip.getDestinyCityFk()+"";
            }

            if(trip.getEmployeeNumFk() != -1){
                sql += " and employee_num_fk ="+trip.getEmployeeNumFk()+"";
            }

            if(trip.getBusIDFk() != -1){
                sql += " and bus_id_fk ="+trip.getBusIDFk()+"";
            }
           
            PreparedStatement statement = conn.prepareStatement(sql);
            System.out.println(statement.toString());
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                SuperModel trp = new TripModel(result.getInt(1), result.getDate(2), result.getInt(3), result.getString(4), result.getString(5), result.getInt(6), result.getInt(7));
                tripsList.add(trp);
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
        return tripsList;
    }
     
     
    public void update(TripModel trip){
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "update trip set trip_date=?, price=?, origin_city_fk=?, destiny_city_fk=?, employee_num_fk=?, bus_id_fk=? where trip_id=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDate(1, trip.getTripDate());
            statement.setInt(2, trip.getPrice());
            statement.setString(3, trip.getOriginCityFk());
            statement.setString(4, trip.getDestinyCityFk());
            statement.setInt(5, trip.getEmployeeNumFk());
            statement.setInt(6, trip.getBusIDFk());
            
            
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                JOptionPane.showMessageDialog(null,"El registro " + trip.getTripID() + " fue modificado exitosamente");
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
    
    public void delete(int tripId){
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "delete from trip where trip_id=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, tripId);
            
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
