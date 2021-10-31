/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Model.EmployeeModel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.json.simple.parser.ParseException;
import utils.ConnectionDB;

/**
 *
 * @author SANTIAGO SIERRA
 */
public class EmployeeDAO {
    private Connection conn = null;
    
    
    public void insertEmployee(EmployeeModel employee) {
        
        try{

            conn = ConnectionDB.getConnection();

            
            String sql = "insert into employee(employee_name) values (?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,employee.getEmployeeName());
            
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
    
    
    public ArrayList<EmployeeModel> getAllEmployees(){
        ArrayList<EmployeeModel> employees = new ArrayList();
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "select * from employee;";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()){
                EmployeeModel employee = new EmployeeModel(result.getInt(1), result.getString(2));
                employees.add(employee);
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
        
        return employees;
    }
    
    public ArrayList<EmployeeModel> getEmployeesByFilter(int employeeNum, String employeeName){
        ArrayList<EmployeeModel> employees = new ArrayList();
        int case_ = -1;
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "select * from employee where employee_name like ?";
            if(employeeNum != -1){
                sql += " and employee_num =?;";
                case_ = 1;
            }
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%"+employeeName+"%"); 
            switch(case_){
                case 1:
                   statement.setInt(2, employeeNum);
                   break;
            }
            System.out.println(statement.toString());
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                EmployeeModel employee = new EmployeeModel(result.getInt(1), result.getString(2));
                employees.add(employee);
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
        return employees;
    }
    
    public void update(int employeeNum, String employeeName){
        
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "update employee set employee_name=? where employee_num=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, employeeName);
            statement.setInt(2, employeeNum);
            
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                JOptionPane.showMessageDialog(null,"El registro " + employeeNum + " fue modificado exitosamente");
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
    
    public void delete(int employeeNum){
        try{

            conn = ConnectionDB.getConnection();
           
            String sql = "delete from employee where employee_num=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, employeeNum);
            
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


//throws IOException, ParseException