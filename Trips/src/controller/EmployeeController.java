/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Access.EmployeeDAO;
import Model.EmployeeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.EmployeeView;

/**
 *
 * @author SANTIAGO SIERRA
 */
public class EmployeeController implements ActionListener{
    
    private EmployeeView empView;
    private EmployeeModel empMod;
    private EmployeeDAO empDAO;
    private int op;
    
    
    public EmployeeController(EmployeeView empView, EmployeeModel empMod, EmployeeDAO empDAO){
        this.empView = empView;
        this.empMod = empMod;
        this.empDAO = empDAO;
        this.op = 0;
    }
    
    public void initialize(){
        empView.setTitle("Employees");
        empView.setLocationRelativeTo(null); //ver cómo funciona si se quita este método
        empView.setVisible(true);
        empView.btnCreate.addActionListener(this);
        empView.btnSearch.addActionListener(this);
        empView.btnUpdate.addActionListener(this);
        empView.btnSearchUpdate.addActionListener(this);
        empView.btnGo.addActionListener(this);
        hideElements();
        setTableResults(empDAO.getAllEmployees());
         
        
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        
        if(e.getSource() == empView.btnCreate){
            hideElements();
            empView.labelEmpName.setVisible(true);
            empView.textFieldEmployeeName.setVisible(true);
            op = 1;
        } else if(e.getSource() == empView.btnSearch){
            hideElements();
            empView.labelEmpNumber.setVisible(true);
            empView.textFieldEmployeeNumber.setVisible(true);
            empView.labelEmpName.setVisible(true);
            empView.textFieldEmployeeName.setVisible(true);
            op = 2;
        } else if(e.getSource() == empView.btnUpdate){
            hideElements();
            empView.labelEmpNumber.setVisible(true);
            empView.textFieldEmployeeNumber.setVisible(true);
            empView.labelEmpName.setVisible(true);
            empView.textFieldEmployeeName.setVisible(true);
            empView.btnSearchUpdate.setVisible(true);
            op = 3;
        }
        
        
        
        
       if(e.getSource() == empView.btnSearchUpdate){
           
           if(!empView.tableEmployees.getSelectionModel().isSelectionEmpty()){
                empView.textFieldEmployeeNumber.setText(empView.tableEmployees.getModel().getValueAt(empView.tableEmployees.getSelectedRow(), 0).toString());
                empView.textFieldEmployeeName.setText(empView.tableEmployees.getModel().getValueAt(empView.tableEmployees.getSelectedRow(), 1).toString());
           }
           
           empMod.setEmployeeName(empView.textFieldEmployeeName.getText());
           if(empView.textFieldEmployeeNumber.getText().equals("")){
                empMod.setEmployeeNum(-1);
           } else {
                empMod.setEmployeeNum(Integer.parseInt(empView.textFieldEmployeeNumber.getText()));
           }
           setTableResults(empDAO.getEmployeesByFilter(empMod.getEmployeeNum(), empMod.getEmployeeName()));
       }
       
       
       
       
       
        
        if(e.getSource() == empView.btnGo){
            
            switch(op){
                case 0: 
                    JOptionPane.showMessageDialog(null, "Select an option from the top pannel to proceed");
                    break;
                    
                case 1:
                    if(!empView.textFieldEmployeeName.getText().equals("")){
                        empMod.setEmployeeName(empView.textFieldEmployeeName.getText());
                        empDAO.insertEmployee(empMod);
                        empView.textFieldEmployeeName.setText("");
                        hideElements();
                        op = 0;
                        setTableResults(empDAO.getAllEmployees());
                        
                    } else {
                        JOptionPane.showMessageDialog(null,"To create an employee the name cannot be empty");
                    }
                   
                    break;
                
                case 2:
                    empMod.setEmployeeName(empView.textFieldEmployeeName.getText());
                    if(empView.textFieldEmployeeNumber.getText().equals("")){
                        empMod.setEmployeeNum(-1);
                    } else {
                        empMod.setEmployeeNum(Integer.parseInt(empView.textFieldEmployeeNumber.getText()));
                    }
                    setTableResults(empDAO.getEmployeesByFilter(empMod.getEmployeeNum(), empMod.getEmployeeName()));
                    empView.textFieldEmployeeName.setText("");
                    empView.textFieldEmployeeNumber.setText("");
                    hideElements();
                    op = 0;
                    break;
                case 3:
                 
                    if(empView.textFieldEmployeeNumber.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Employee Number must be populated to perform the change");
                    } else if(empView.textFieldEmployeeName.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Employee Name must be populated to perform the change");
                    } else {
                        empMod.setEmployeeNum(Integer.parseInt(empView.textFieldEmployeeNumber.getText()));
                        empMod.setEmployeeName(empView.textFieldEmployeeName.getText());
                        empDAO.update(empMod.getEmployeeNum(), empMod.getEmployeeName());
                        empView.textFieldEmployeeNumber.setText("");
                        empView.textFieldEmployeeName.setText("");
                        hideElements();
                        op = 0;
                        setTableResults(empDAO.getAllEmployees());
                    }
                    break;
            }   
                
                
        }
        
        
        
    }
    
    public void hideElements(){
        empView.labelEmpNumber.setVisible(false);
        empView.textFieldEmployeeNumber.setVisible(false);
        empView.labelEmpName.setVisible(false);
        empView.textFieldEmployeeName.setVisible(false);
        empView.btnSearchUpdate.setVisible(false);
    }
    
    
    public void setTableResults(ArrayList<EmployeeModel> employees){
        String[] headers = {"Employee Number", "Employee Name"};
        empView.tableEmployees.removeAll();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(headers);
        empView.tableEmployees.setModel(tableModel);
        for(int i=0; i<employees.size(); i++){
            tableModel.addRow(employees.get(i).toArray());
        }
        
    }
    
    
}
