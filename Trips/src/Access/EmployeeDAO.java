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
import java.sql.SQLException;
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
            if(conn == null){
                conn = ConnectionDB.getConnection();
            }
            
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
}


//throws IOException, ParseException