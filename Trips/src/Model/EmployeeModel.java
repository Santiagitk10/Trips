/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author SANTIAGO SIERRA
 */
public class EmployeeModel {
    
    private int employeeNum;
    private String employeeName;
    
    
    public EmployeeModel(){
        this.employeeName = "";
    }

    public EmployeeModel(int employeeNum, String employeeName){
        this.employeeNum = employeeNum;
        this.employeeName = employeeName;
    }
    
    
    public int getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(int employeeNum) {
        this.employeeNum = employeeNum;
    }
    

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Object[] toArray(){
        Object[] data = {employeeNum, employeeName};
        return data;
    }
    
    
    
    
    
}
