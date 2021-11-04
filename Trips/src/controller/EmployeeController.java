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
import view.MainFrame;

/**
 *
 * @author SANTIAGO SIERRA
 */
public class EmployeeController implements ActionListener{
    
    private MainFrame mainframe;
    private EmployeeModel empMod;
    private EmployeeDAO empDAO;
    private int op;
    private int currEntity;
    
    
    public EmployeeController(MainFrame mainframe, EmployeeModel empMod, EmployeeDAO empDAO){
        this.mainframe = mainframe;
        this.empMod = empMod;
        this.empDAO = empDAO;
        this.op = 0;
        this.currEntity = 0;
        
    }
    
    public void initialize(){
        mainframe.setTitle("Trips");
        mainframe.setLocationRelativeTo(null); //ver cómo funciona si se quita este método
        mainframe.setVisible(true);
        mainframe.dataDisplayPane.removeAll();
        mainframe.dataDisplayPane.revalidate();
        mainframe.dataDisplayPane.repaint();
        mainframe.btnEmpPanel.addActionListener(this);
        mainframe.btnCreate.addActionListener(this);
        mainframe.btnSearch.addActionListener(this);
        mainframe.btnUpdate.addActionListener(this);
        mainframe.btnSearchSelectEmp.addActionListener(this);
        mainframe.btnDelete.addActionListener(this);
        mainframe.btnGo.addActionListener(this);
        mainframe.btnClearFields.addActionListener(this);
        hideElements();
        setTableResults(empDAO.getAllEmployees());
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == mainframe.btnEmpPanel){
            mainframe.dataDisplayPane.removeAll();
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            mainframe.dataDisplayPane.add(mainframe.EmpPane);
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            currEntity = 1;   
            System.out.println(currEntity);
        }
        

        
        switch(currEntity){
            case 1:
                if(e.getSource() == mainframe.btnCreate){
                    hideElements();
                    mainframe.labelEmpName.setVisible(true);
                    mainframe.textFieldEmployeeName.setVisible(true);
                    op = 1;
                } else if(e.getSource() == mainframe.btnSearch){
                    hideElements();
                    mainframe.labelEmpNumber.setVisible(true);
                    mainframe.textFieldEmployeeNumber.setVisible(true);
                    mainframe.labelEmpName.setVisible(true);
                    mainframe.textFieldEmployeeName.setVisible(true);
                    op = 2;
                } else if(e.getSource() == mainframe.btnUpdate){
                    hideElements();
                    mainframe.labelEmpNumber.setVisible(true);
                    mainframe.textFieldEmployeeNumber.setVisible(true);
                    mainframe.labelEmpName.setVisible(true);
                    mainframe.textFieldEmployeeName.setVisible(true);
                    mainframe.btnSearchSelectEmp.setVisible(true);
                    op = 3;
                } else if(e.getSource() == mainframe.btnDelete){
                    hideElements();
                    mainframe.labelEmpNumber.setVisible(true);
                    mainframe.textFieldEmployeeNumber.setVisible(true);
                    mainframe.labelEmpName.setVisible(true);
                    mainframe.textFieldEmployeeName.setVisible(true);
                    mainframe.btnSearchSelectEmp.setVisible(true);
                    op = 4;

                } else if (e.getSource() == mainframe.btnClearFields){
                    mainframe.textFieldEmployeeNumber.setText("");
                    mainframe.textFieldEmployeeName.setText("");
                }

            break;
        }
        
        
        
        
        
        
       if(e.getSource() == mainframe.btnSearchSelectEmp){
           
           if(op == 3){
               if(!mainframe.tableEmployees.getSelectionModel().isSelectionEmpty()){
                    mainframe.textFieldEmployeeNumber.setText(mainframe.tableEmployees.getModel().getValueAt(mainframe.tableEmployees.getSelectedRow(), 0).toString());
                    mainframe.textFieldEmployeeName.setText(mainframe.tableEmployees.getModel().getValueAt(mainframe.tableEmployees.getSelectedRow(), 1).toString());
                }
           }   
            empMod.setEmployeeName(mainframe.textFieldEmployeeName.getText());
            if(mainframe.textFieldEmployeeNumber.getText().equals("")){
                 empMod.setEmployeeNum(-1);
            } else {
                 empMod.setEmployeeNum(Integer.parseInt(mainframe.textFieldEmployeeNumber.getText()));
            }
            
            setTableResults(empDAO.getEmployeesByFilter(empMod.getEmployeeNum(), empMod.getEmployeeName()));
               
        }
       
       if(e.getSource() == mainframe.btnGo){
            
            switch(op){
                case 0: 
                    JOptionPane.showMessageDialog(null, "Select an option from the left pannel to proceed");
                    break;
                    
                case 1:
                    if(!mainframe.textFieldEmployeeName.getText().equals("")){
                        empMod.setEmployeeName(mainframe.textFieldEmployeeName.getText());
                        empDAO.insertEmployee(empMod);
                        mainframe.textFieldEmployeeName.setText("");
                        hideElements();
                        op = 0;
                        setTableResults(empDAO.getAllEmployees());
                        
                    } else {
                        JOptionPane.showMessageDialog(null,"To create an employee the name cannot be empty");
                    }
                   
                    break;
                
                case 2:
                    empMod.setEmployeeName(mainframe.textFieldEmployeeName.getText());
                    if(mainframe.textFieldEmployeeNumber.getText().equals("")){
                        empMod.setEmployeeNum(-1);
                    } else {
                        empMod.setEmployeeNum(Integer.parseInt(mainframe.textFieldEmployeeNumber.getText()));
                    }
                    setTableResults(empDAO.getEmployeesByFilter(empMod.getEmployeeNum(), empMod.getEmployeeName()));
                    mainframe.textFieldEmployeeName.setText("");
                    mainframe.textFieldEmployeeNumber.setText("");
                    hideElements();
                    op = 0;
                    break;
                case 3:
                 
                    if(mainframe.textFieldEmployeeNumber.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Employee Number must be populated to perform the change");
                    } else if(mainframe.textFieldEmployeeName.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Employee Name must be populated to perform the change");
                    } else {
                        empMod.setEmployeeNum(Integer.parseInt(mainframe.textFieldEmployeeNumber.getText()));
                        empMod.setEmployeeName(mainframe.textFieldEmployeeName.getText());
                        empDAO.update(empMod.getEmployeeNum(), empMod.getEmployeeName());
                        mainframe.textFieldEmployeeNumber.setText("");
                        mainframe.textFieldEmployeeName.setText("");
                        hideElements();
                        op = 0;
                        setTableResults(empDAO.getAllEmployees());
                    }
                    break;
                case 4: 
                    if(!mainframe.tableEmployees.getSelectionModel().isSelectionEmpty()){
                        
                        int response = JOptionPane.showConfirmDialog(mainframe.btnGo, "Are you sure you want to delete the record?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(response == JOptionPane.YES_OPTION ){
                             empMod.setEmployeeNum(Integer.parseInt(mainframe.tableEmployees.getModel().getValueAt(mainframe.tableEmployees.getSelectedRow(), 0).toString()));
                             empDAO.delete(empMod.getEmployeeNum());
                             mainframe.textFieldEmployeeNumber.setText("");
                             mainframe.textFieldEmployeeName.setText("");
                             hideElements();
                             op = 0;
                             setTableResults(empDAO.getAllEmployees());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Select one register from the table to delete it");
                    }
                    break;  
            }   
                
                
        }    
    
        
    }
    public void hideElements(){
        mainframe.labelEmpNumber.setVisible(false);
        mainframe.textFieldEmployeeNumber.setVisible(false);
        mainframe.labelEmpName.setVisible(false);
        mainframe.textFieldEmployeeName.setVisible(false);
        mainframe.btnSearchSelectEmp.setVisible(false);
       }   
//    
//    
    public void setTableResults(ArrayList<EmployeeModel> employees){
        String[] headers = {"Employee Number", "Employee Name"};
        mainframe.tableEmployees.removeAll();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(headers);
        mainframe.tableEmployees.setModel(tableModel);
        for(int i=0; i<employees.size(); i++){
            tableModel.addRow(employees.get(i).toArray());
        }
        
        
        
        
        
    }
}
    
