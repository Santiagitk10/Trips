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
import view.EmployeeView;

/**
 *
 * @author SANTIAGO SIERRA
 */
public class EmployeeController implements ActionListener{
    
    private EmployeeView empView;
    private EmployeeModel empMod;
    private EmployeeDAO empDAO;
    
    
    public EmployeeController(EmployeeView empView, EmployeeModel empMod, EmployeeDAO empDAO){
        this.empView = empView;
        this.empMod = empMod;
        this.empDAO = empDAO;
        this.empView.btnGo.addActionListener(this);
    }
    
    public void initialize(){
        empView.setTitle("Employees");
        empView.setLocationRelativeTo(null); //ver cómo funciona si se quita este método
        empView.setVisible(true);
//        empView.textFieldEmployeeNumber.setVisible(false); //ver si puedo utilizar esto para poner grayed out el filed de num cuando sea una creación
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(empView.rdioBtnCreate.isSelected()){
            System.out.println("Create selected");
            empView.textFieldEmployeeNumber.setVisible(false);
            empView.labelEmpNumber.setVisible(false);
            if(e.getSource() == empView.btnGo){
                empMod.setEmployeeName(empView.textFieldEmployeeName.getText());
                empDAO.insertEmployee(empMod);
                clearFields();
            }
        }
        
        
    }
    
    public void clearFields(){
        empView.textFieldEmployeeNumber.setText(null);
        empView.textFieldEmployeeName.setText(null);
    }
}
