/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Access.BusDAO;
import Access.CityDAO;
import Access.EmployeeDAO;
import Access.LuggageDAO;
import Access.PassengerDAO;
import Access.TripDAO;
import Model.BusModel;
import Model.CityModel;
import Model.EmployeeModel;
import Model.LuggageModel;
import Model.PassengerModel;
import Model.SuperModel;
import Model.TripModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Date;
//import java.util.HashSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import view.MainFrame;

/**
 *
 * @author SANTIAGO SIERRA
 */
public class Controller implements ActionListener{
    
    private MainFrame mainframe;
    private EmployeeModel empMod;
    private EmployeeDAO empDAO;
    private PassengerModel pasMod;
    private PassengerDAO pasDAO;
    private LuggageModel lugMod;
    private LuggageDAO lugDAO;
    private BusModel busMod;
    private BusDAO busDAO;
    private CityModel cityMod;
    private CityDAO cityDAO;
    private TripModel tripMod;
    private TripDAO tripDAO;
    private int op;
    private int currEntity;
    //Comboboxes en Luggage
    private JComboBox comboxLugStatus;
    private JComboBox comboxPassIdFk;
    //Comboboxes en Buses
    private JComboBox comboxBusSeatCap;
    //Comboboxes en Trips
    private JComboBox comboxEmpNumFk;
    private JComboBox comboxOriginCityFk;
    private JComboBox comboxDestinyCityFk;
    private JComboBox comboxBusIDFk;
    
    
    
    
    public Controller(MainFrame mainframe, EmployeeModel empMod, EmployeeDAO empDAO, PassengerModel pasMod, PassengerDAO pasDAO, LuggageModel lugMod, LuggageDAO lugDAO, BusModel busMod, BusDAO busDAO, CityModel cityMod, CityDAO cityDAO, TripModel tripMod, TripDAO tripDAO ){
        this.mainframe = mainframe;
        this.empMod = empMod;
        this.empDAO = empDAO;
        this.pasMod = pasMod;
        this.pasDAO = pasDAO;
        this.lugMod = lugMod;
        this.lugDAO = lugDAO;
        this.busMod = busMod;
        this.busDAO = busDAO;
        this.cityMod = cityMod;
        this.cityDAO = cityDAO;
        this.tripMod = tripMod;
        this.tripDAO = tripDAO;
        this.op = 0;
        this.currEntity = 0;
        
    }
    
    public void initialize(){
        mainframe.setTitle("Trips");
        mainframe.setLocationRelativeTo(null);
        mainframe.setVisible(true);
        mainframe.dataDisplayPane.removeAll();
        mainframe.dataDisplayPane.revalidate();
        mainframe.dataDisplayPane.repaint();
        
        mainframe.btnEmpPanel.addActionListener(this);
        mainframe.btnPasPanel.addActionListener(this);
        mainframe.btnLugPanel.addActionListener(this);
        mainframe.btnBusPanel.addActionListener(this);
        mainframe.btnCitPanel.addActionListener(this);
        mainframe.btnTripPanel.addActionListener(this);
        
        mainframe.btnCreate.addActionListener(this);
        mainframe.btnSearch.addActionListener(this);
        mainframe.btnUpdate.addActionListener(this);
        mainframe.btnSearchSelectEmp.addActionListener(this);
        mainframe.btnSearchSelectPas.addActionListener(this);
        mainframe.btnSearchSelectLug.addActionListener(this);
        mainframe.btnSearchSelectBus.addActionListener(this);
        mainframe.btnSearchSelectCit.addActionListener(this);
        mainframe.btnDelete.addActionListener(this);
        mainframe.btnGo.addActionListener(this);
        mainframe.btnClearFields.addActionListener(this);
        
        
        InitialDataComboBoxes initialData = new InitialDataComboBoxes();
        
        //Creación combobox en Luggage para seleción de Status
        String[] lugStatusOptions = {"ALL", "OK", "LOST"};
        this.comboxLugStatus = new JComboBox(lugStatusOptions);
        this.comboxLugStatus.setBounds(340, 143, 100, 30);
        mainframe.LugPane.add(this.comboxLugStatus);
        
        
        //Creación combobox en Luggage para selección de PassenderIdFk
        this.comboxPassIdFk = new JComboBox();        
        this.comboxPassIdFk.setModel(new DefaultComboBoxModel<>(initialData.getPassengers().toArray(new SuperModel[initialData.getPassengers().size()])));
        this.comboxPassIdFk.setSelectedIndex(0);
        this.comboxPassIdFk.setBounds(340, 200, 200, 30);
        mainframe.LugPane.add(this.comboxPassIdFk);     
        
        //Creación combobox en Buses para selección capacidad
        Object[] busSeatCapOptions = {"ALL",30,40,45,90};
        this.comboxBusSeatCap = new JComboBox(busSeatCapOptions);
        this.comboxBusSeatCap.setBounds(375, 140, 100, 30);
        mainframe.BusPane.add(this.comboxBusSeatCap);
        
        
        //Creación combobox en Trips para selección de employeeNumFk
        this.comboxEmpNumFk = new JComboBox();
        this.comboxEmpNumFk.setModel(new DefaultComboBoxModel<>(initialData.getEmployees().toArray(new SuperModel[initialData.getEmployees().size()])));
        this.comboxEmpNumFk.setSelectedIndex(0);
        this.comboxEmpNumFk.setBounds(450, 140, 60, 30);
        mainframe.TripPane.add(this.comboxEmpNumFk);
        
        //Creación combobox en Trips para selección de originCityFk
        this.comboxOriginCityFk = new JComboBox();
        this.comboxOriginCityFk.setModel(new DefaultComboBoxModel<>(initialData.getCities().toArray(new SuperModel[initialData.getCities().size()])));
        this.comboxOriginCityFk.setSelectedIndex(0);
        this.comboxOriginCityFk.setBounds(450, 65, 140, 30);
        mainframe.TripPane.add(this.comboxOriginCityFk);
        
        //Creación combobox en Trips para selección de destinyCityFk
        this.comboxDestinyCityFk = new JComboBox();
        this.comboxDestinyCityFk.setModel(new DefaultComboBoxModel<>(initialData.getCities().toArray(new SuperModel[initialData.getCities().size()])));
        this.comboxDestinyCityFk.setSelectedIndex(0);
        this.comboxDestinyCityFk.setBounds(450, 102, 140, 30);
        mainframe.TripPane.add(this.comboxDestinyCityFk);
        
        
        //Creación combobox en Trips para selección de busIDFK;
        this.comboxBusIDFk = new JComboBox();
        this.comboxBusIDFk.setModel(new DefaultComboBoxModel<>(initialData.getBuses().toArray(new SuperModel[initialData.getBuses().size()])));
        this.comboxBusIDFk.setSelectedIndex(0);
        this.comboxBusIDFk.setBounds(450, 174, 60, 30);
        mainframe.TripPane.add(this.comboxBusIDFk);
        
        
        hideElements();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        
        //Initial selection depending on the entity the user wants to work with
        String[] Employeeheaders = {"Passenger ID", "Passenger Name"};
        String[] Passengerheaders = {"Passenger ID", "Passenger Name"};
        String[] Luggageheaders = {"Luggage ID", "Luggage Status", "Passenger ID"};
        String[] busheaders = {"Bus ID", "Seat Capacity"};
        String[] cityheaders = {"City Name"};
        String[] tripheaders = {"Trip ID", "Trip Date", "Price", "Origin City", "Destiny City", "Emp Number", "Bus ID"};
        
        
        if(e.getSource() == mainframe.btnEmpPanel){
            mainframe.dataDisplayPane.removeAll();
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            setTableResults(empDAO.getAllEmployees(), Employeeheaders,mainframe.tableEmployees);
            mainframe.dataDisplayPane.add(mainframe.EmpPane);
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            currEntity = 1;
        } else if(e.getSource() == mainframe.btnPasPanel){
            mainframe.dataDisplayPane.removeAll();
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            setTableResults(pasDAO.getAllPassengers(),Passengerheaders, mainframe.tablePassengers );
            mainframe.dataDisplayPane.add(mainframe.PasPane);
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            currEntity = 2;
        } else if(e.getSource() == mainframe.btnLugPanel){
            mainframe.dataDisplayPane.removeAll();
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            setTableResults(lugDAO.getAllLuggage(),Luggageheaders, mainframe.tableLuggage);
            mainframe.dataDisplayPane.add(mainframe.LugPane);
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            currEntity = 3;
            
            
        } else if(e.getSource() == mainframe.btnBusPanel){
            mainframe.dataDisplayPane.removeAll();
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            setTableResults(busDAO.getAllBuses(),busheaders, mainframe.tableBuses);
            mainframe.dataDisplayPane.add(mainframe.BusPane);
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            currEntity = 4;   
            
        } else if(e.getSource() == mainframe.btnCitPanel){
            mainframe.dataDisplayPane.removeAll();
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            setTableResults(cityDAO.getAllCities(),cityheaders, mainframe.tableCities);
            mainframe.dataDisplayPane.add(mainframe.CitPane);
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            currEntity = 5;
            
        } else if(e.getSource() == mainframe.btnTripPanel){
            mainframe.dataDisplayPane.removeAll();
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            setTableResults(tripDAO.getAllTrips(), tripheaders, mainframe.tableTrips);
            mainframe.dataDisplayPane.add(mainframe.TripPane);
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            currEntity = 6;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        //Selection of which elements to show depending on which crud option is selected
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
            case 2: 
               if(e.getSource() == mainframe.btnCreate){
                    hideElements();
                    mainframe.labelPasID.setVisible(true);
                    mainframe.txtFieldPassID.setVisible(true);
                    mainframe.labelPasName.setVisible(true);
                    mainframe.txtFieldPassName.setVisible(true);
                    op = 5;
                } else if(e.getSource() == mainframe.btnSearch){
                    hideElements();
                    mainframe.labelPasID.setVisible(true);
                    mainframe.txtFieldPassID.setVisible(true);
                    mainframe.labelPasName.setVisible(true);
                    mainframe.txtFieldPassName.setVisible(true);
                    op = 6;
                } else if(e.getSource() == mainframe.btnUpdate){
                    hideElements();
                    mainframe.labelPasID.setVisible(true);
                    mainframe.txtFieldPassID.setVisible(true);
                    mainframe.labelPasName.setVisible(true);
                    mainframe.txtFieldPassName.setVisible(true);
                    mainframe.btnSearchSelectPas.setVisible(true);
                    op = 7;
                } else if(e.getSource() == mainframe.btnDelete){
                    hideElements();
                    mainframe.labelPasID.setVisible(true);
                    mainframe.txtFieldPassID.setVisible(true);
                    mainframe.labelPasName.setVisible(true);
                    mainframe.txtFieldPassName.setVisible(true);
                    mainframe.btnSearchSelectPas.setVisible(true);
                    op = 8;

                } else if (e.getSource() == mainframe.btnClearFields){
                    mainframe.txtFieldPassID.setText("");
                    mainframe.txtFieldPassName.setText("");
                }
            break;   
            case 3:
                if(e.getSource() == mainframe.btnCreate){
                    hideElements();
                    mainframe.labelLugStatus.setVisible(true);
                    mainframe.labelPasIDFkLug.setVisible(true);
                    comboxLugStatus.setVisible(true);
                    comboxPassIdFk.setVisible(true);
                    op = 9;
                } else if(e.getSource() == mainframe.btnSearch){
                    hideElements();
                    mainframe.labelLugID.setVisible(true);
                    mainframe.txtFieldLugID.setVisible(true);
                    mainframe.labelLugStatus.setVisible(true);
                    mainframe.labelPasIDFkLug.setVisible(true);
                    comboxLugStatus.setVisible(true);
                    comboxPassIdFk.setVisible(true);
                    op = 10;
                } else if(e.getSource() == mainframe.btnUpdate){
                    hideElements();
                    mainframe.labelLugID.setVisible(true);
                    mainframe.txtFieldLugID.setVisible(true);
                    mainframe.labelLugStatus.setVisible(true);
                    mainframe.labelPasIDFkLug.setVisible(true);
                    mainframe.btnSearchSelectLug.setVisible(true);
                    comboxLugStatus.setVisible(true);
                    comboxPassIdFk.setVisible(true);
                    op = 11;
                } else if(e.getSource() == mainframe.btnDelete){
                    hideElements();
                    mainframe.labelLugID.setVisible(true);
                    mainframe.txtFieldLugID.setVisible(true);
                    mainframe.labelLugStatus.setVisible(true);
                    mainframe.labelPasIDFkLug.setVisible(true);
                    mainframe.btnSearchSelectLug.setVisible(true);
                    comboxLugStatus.setVisible(true);
                    comboxPassIdFk.setVisible(true);
                    op = 12;

                } else if (e.getSource() == mainframe.btnClearFields){
                    mainframe.txtFieldLugID.setText("");
                    comboxLugStatus.setSelectedIndex(0);
                    comboxPassIdFk.setSelectedIndex(0);
                }
            break;   
            case 4: 
                if(e.getSource() == mainframe.btnCreate){
                    hideElements();
                    mainframe.labelBusSeatCap.setVisible(true);
                    comboxBusSeatCap.setVisible(true);
                    op = 13;
                } else if(e.getSource() == mainframe.btnSearch){
                    hideElements();
                    mainframe.labelBusID.setVisible(true);
                    mainframe.textFieldBusID.setVisible(true);
                    mainframe.labelBusSeatCap.setVisible(true);
                    comboxBusSeatCap.setVisible(true);
                    op = 14;
                } else if(e.getSource() == mainframe.btnUpdate){
                    hideElements();
                    mainframe.labelBusID.setVisible(true);
                    mainframe.textFieldBusID.setVisible(true);
                    mainframe.labelBusSeatCap.setVisible(true);
                    comboxBusSeatCap.setVisible(true);
                    mainframe.btnSearchSelectBus.setVisible(true);
                    op = 15;
                } else if(e.getSource() == mainframe.btnDelete){
                    hideElements();
                    mainframe.labelBusID.setVisible(true);
                    mainframe.textFieldBusID.setVisible(true);
                    mainframe.labelBusSeatCap.setVisible(true);
                    comboxBusSeatCap.setVisible(true);
                    mainframe.btnSearchSelectBus.setVisible(true);
                    op = 16;

                } else if (e.getSource() == mainframe.btnClearFields){
                    mainframe.textFieldBusID.setText("");
                    comboxBusSeatCap.setSelectedIndex(0);
                }
            break;
            case 5: 
                if(e.getSource() == mainframe.btnCreate){
                    hideElements();
                    mainframe.labelCitName.setVisible(true);
                    mainframe.textFieldCityName.setVisible(true);
                    op = 17;
                } else if(e.getSource() == mainframe.btnSearch){
                    hideElements();
                    mainframe.labelCitName.setVisible(true);
                    mainframe.textFieldCityName.setVisible(true);
                    op = 18;
                } else if(e.getSource() == mainframe.btnUpdate){
                    hideElements();
                    mainframe.labelCitName.setVisible(true);
                    mainframe.textFieldCityName.setVisible(true);
                    mainframe.btnSearchSelectCit.setVisible(true);
                    op = 19;
                } else if(e.getSource() == mainframe.btnDelete){
                    hideElements();
                    mainframe.labelCitName.setVisible(true);
                    mainframe.textFieldCityName.setVisible(true);
                    mainframe.btnSearchSelectCit.setVisible(true);
                    op = 20;

                } else if (e.getSource() == mainframe.btnClearFields){
                    mainframe.textFieldCityName.setText("");
                }
            break;
            case 6: 
                if(e.getSource() == mainframe.btnCreate){
                    hideElements();
                    mainframe.labelTripPrice.setVisible(true);
                    mainframe.textFieldTripsPrice.setVisible(true);
                    mainframe.labelTripDate.setVisible(true);
                    mainframe.DateChooserTripDate.setVisible(true);
                    mainframe.labelEmpNumFkTrip.setVisible(true);
                    comboxEmpNumFk.setVisible(true);
                    mainframe.labelBusIDFkTrip.setVisible(true);
                    comboxBusIDFk.setVisible(true);
                    mainframe.labelOriCityFkTrip.setVisible(true);
                    comboxOriginCityFk.setVisible(true);
                    mainframe.labelDesCityFkTrip.setVisible(true);
                    comboxDestinyCityFk.setVisible(true);
                    op = 21;
                } else if(e.getSource() == mainframe.btnSearch){
                    hideElements();
                    mainframe.labelTripID.setVisible(true);
                    mainframe.textFieldTripID.setVisible(true);
                    mainframe.labelTripPrice.setVisible(true);
                    mainframe.textFieldTripsPrice.setVisible(true);
                    mainframe.labelTripDate.setVisible(true);
                    mainframe.DateChooserTripDate.setVisible(true);
                    mainframe.labelEmpNumFkTrip.setVisible(true);
                    comboxEmpNumFk.setVisible(true);
                    mainframe.labelBusIDFkTrip.setVisible(true);
                    comboxBusIDFk.setVisible(true);
                    mainframe.labelOriCityFkTrip.setVisible(true);
                    comboxOriginCityFk.setVisible(true);
                    mainframe.labelDesCityFkTrip.setVisible(true);
                    comboxDestinyCityFk.setVisible(true);
                    op = 22;
                } else if(e.getSource() == mainframe.btnUpdate){
                    hideElements();
                    mainframe.labelTripID.setVisible(true);
                    mainframe.textFieldTripID.setVisible(true);
                    mainframe.labelTripPrice.setVisible(true);
                    mainframe.textFieldTripsPrice.setVisible(true);
                    mainframe.labelTripDate.setVisible(true);
                    mainframe.DateChooserTripDate.setVisible(true);
                    mainframe.labelEmpNumFkTrip.setVisible(true);
                    comboxEmpNumFk.setVisible(true);
                    mainframe.labelBusIDFkTrip.setVisible(true);
                    comboxBusIDFk.setVisible(true);
                    mainframe.labelOriCityFkTrip.setVisible(true);
                    comboxOriginCityFk.setVisible(true);
                    mainframe.labelDesCityFkTrip.setVisible(true);
                    comboxDestinyCityFk.setVisible(true);
                    mainframe.btnSearchSelectTrip.setVisible(true);
                    op = 23;
                } else if(e.getSource() == mainframe.btnDelete){
                    hideElements();
                    mainframe.labelTripID.setVisible(true);
                    mainframe.textFieldTripID.setVisible(true);
                    mainframe.labelTripPrice.setVisible(true);
                    mainframe.textFieldTripsPrice.setVisible(true);
                    mainframe.labelTripDate.setVisible(true);
                    mainframe.DateChooserTripDate.setVisible(true);
                    mainframe.labelEmpNumFkTrip.setVisible(true);
                    comboxEmpNumFk.setVisible(true);
                    mainframe.labelBusIDFkTrip.setVisible(true);
                    comboxBusIDFk.setVisible(true);
                    mainframe.labelOriCityFkTrip.setVisible(true);
                    comboxOriginCityFk.setVisible(true);
                    mainframe.labelDesCityFkTrip.setVisible(true);
                    comboxDestinyCityFk.setVisible(true);
                    mainframe.btnSearchSelectTrip.setVisible(true);
                    op = 24;

                } else if (e.getSource() == mainframe.btnClearFields){
                    mainframe.textFieldTripID.setText("");
                    mainframe.textFieldTripsPrice.setText("");
                    mainframe.DateChooserTripDate.setDate(null); 
                    comboxEmpNumFk.setSelectedIndex(0);
                    comboxOriginCityFk.setSelectedIndex(0);
                    comboxDestinyCityFk.setSelectedIndex(0);
                    comboxBusIDFk.setSelectedIndex(0);
                }
            break;
        }
        
        
                    
                    
        
        
        
        
       //Search/Select Buttons functionality
       if(e.getSource() == mainframe.btnSearchSelectEmp){
           
           if(op == 3 || op == 4){
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
            
            setTableResults(empDAO.getEmployeesByFilter(empMod.getEmployeeNum(), empMod.getEmployeeName()), Employeeheaders, mainframe.tableEmployees);  
       }
       
       if(e.getSource() == mainframe.btnSearchSelectPas){
           if(op == 7 || op == 8){
               if(!mainframe.tablePassengers.getSelectionModel().isSelectionEmpty()){
                    mainframe.txtFieldPassID.setText(mainframe.tablePassengers.getModel().getValueAt(mainframe.tablePassengers.getSelectedRow(), 0).toString());
                    mainframe.txtFieldPassName.setText(mainframe.tablePassengers.getModel().getValueAt(mainframe.tablePassengers.getSelectedRow(), 1).toString());
                }
           }
           
           pasMod.setPassengerName(mainframe.txtFieldPassName.getText());
            if(mainframe.txtFieldPassID.getText().equals("")){
                pasMod.setPassengerId(-1);
            } else {
                 pasMod.setPassengerId(Long.parseLong(mainframe.txtFieldPassID.getText()));
            }
                
            setTableResults(pasDAO.getPassengersByFilter(pasMod.getPassengerId(), pasMod.getPassengerName()), Passengerheaders, mainframe.tablePassengers);
       }
       
       
       if(e.getSource() == mainframe.btnSearchSelectLug){
           if(op == 11 || op == 12){
               if(!mainframe.tableLuggage.getSelectionModel().isSelectionEmpty()){
                    mainframe.txtFieldLugID.setText(mainframe.tableLuggage.getModel().getValueAt(mainframe.tableLuggage.getSelectedRow(), 0).toString());
                    comboxLugStatus.setSelectedItem(mainframe.tableLuggage.getModel().getValueAt(mainframe.tableLuggage.getSelectedRow(), 1).toString());
                    
                    String selectedData =  mainframe.tableLuggage.getModel().getValueAt(mainframe.tableLuggage.getSelectedRow(), 2).toString(); 
       
                    for(int i = 0; i < comboxPassIdFk.getItemCount(); i++ ){
                        if(comboxPassIdFk.getItemAt(i).toString().equals(selectedData)){
                            comboxPassIdFk.setSelectedIndex(i);
                        }
                    }
                   
                    
                }
           }
           
           lugMod.setLuggageStatus(comboxLugStatus.getSelectedItem().toString());
            if(mainframe.txtFieldLugID.getText().equals("")){
                lugMod.setLuggageID(-1);
            } else {
                 lugMod.setLuggageID(Integer.parseInt(mainframe.txtFieldLugID.getText()));
            }
                
                
            if(comboxPassIdFk.getSelectedItem().toString().equals("0")) {
               lugMod.setPassengerIDFk(-1);
            } else {
                lugMod.setPassengerIDFk(Long.parseLong(comboxPassIdFk.getSelectedItem().toString()));
            }
    
                
                
            setTableResults(lugDAO.getLuggageByFilter(lugMod.getLuggageID(), lugMod.getLuggageStatus(), lugMod.getPassengerIDFk()), Luggageheaders, mainframe.tableLuggage);
       }
       
       
       if(e.getSource() == mainframe.btnSearchSelectBus){
           
           if(op == 15 || op == 16){
               System.out.println("dentro");
               if(!mainframe.tableBuses.getSelectionModel().isSelectionEmpty()){
                    mainframe.textFieldBusID.setText(mainframe.tableBuses.getModel().getValueAt(mainframe.tableBuses.getSelectedRow(), 0).toString());
                    comboxBusSeatCap.setSelectedItem(mainframe.tableBuses.getModel().getValueAt(mainframe.tableBuses.getSelectedRow(), 1));
                }
             
            }
           
           
            if(mainframe.textFieldBusID.getText().equals("") && comboxBusSeatCap.getSelectedItem().equals("ALL")){
                setTableResults(busDAO.getAllBuses(),busheaders, mainframe.tableBuses);

            } else if(mainframe.textFieldBusID.getText().equals("")){
                busMod.setBusID(-1);
                busMod.setSeatCapacity(Integer.parseInt(comboxBusSeatCap.getSelectedItem().toString()));
                setTableResults(busDAO.getBusesByFilter(busMod.getBusID(), busMod.getSeatCapacity()),busheaders, mainframe.tableBuses);
            } else if(comboxBusSeatCap.getSelectedItem().equals("ALL")){
                busMod.setSeatCapacity(-1);
                busMod.setBusID(Integer.parseInt(mainframe.textFieldBusID.getText()));
                setTableResults(busDAO.getBusesByFilter(busMod.getBusID(), busMod.getSeatCapacity()),busheaders, mainframe.tableBuses);
            } else {
                busMod.setBusID(Integer.parseInt(mainframe.textFieldBusID.getText()));
                busMod.setSeatCapacity(Integer.parseInt(comboxBusSeatCap.getSelectedItem().toString()));
                setTableResults(busDAO.getBusesByFilter(busMod.getBusID(), busMod.getSeatCapacity()),busheaders, mainframe.tableBuses);
            }
            
            
       }
       
       
       
       if(e.getSource() == mainframe.btnSearchSelectCit){
           
           if(op == 19 || op == 20){
               if(!mainframe.tableCities.getSelectionModel().isSelectionEmpty()){
                    mainframe.textFieldCityName.setText(mainframe.tableCities.getModel().getValueAt(mainframe.tableCities.getSelectedRow(), 0).toString());
               }
           }
           
           
            cityMod.setCityName(mainframe.textFieldCityName.getText());
            setTableResults(cityDAO.getCitiesByFilter(cityMod.getCityName()), cityheaders, mainframe.tableCities); 
       }
       
       
       
       
       
       
       
       
       
       
       
       
       
       //Crud action to perform once Go! is clicked
       if(e.getSource() == mainframe.btnGo){
            
            switch(op){
                case 0: 
                    JOptionPane.showMessageDialog(null, "Select an option from the left pannel to proceed");
                    break;
                //Crud actions for the employee 
                case 1:
                    if(!mainframe.textFieldEmployeeName.getText().equals("")){
                        empMod.setEmployeeName(mainframe.textFieldEmployeeName.getText());
                        empDAO.insertEmployee(empMod);
                        mainframe.textFieldEmployeeName.setText("");
                        hideElements();
                        op = 0;
                        setTableResults(empDAO.getAllEmployees(), Employeeheaders,mainframe.tableEmployees);
                        
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
                    setTableResults(empDAO.getEmployeesByFilter(empMod.getEmployeeNum(), empMod.getEmployeeName()), Employeeheaders, mainframe.tableEmployees); 
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
                        setTableResults(empDAO.getAllEmployees(), Employeeheaders,mainframe.tableEmployees);
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
                             setTableResults(empDAO.getAllEmployees(), Employeeheaders,mainframe.tableEmployees);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Select one register from the table to delete it");
                    }
                    break;  
                //Crud actions for the passenger  
                case 5:
                    if(!mainframe.txtFieldPassName.getText().equals("") && !mainframe.txtFieldPassID.getText().equals("") ){
                        pasMod.setPassengerId(Long.parseLong(mainframe.txtFieldPassID.getText()));
                        pasMod.setPassengerName(mainframe.txtFieldPassName.getText());
                        pasDAO.insertPassenger(pasMod);
                        mainframe.txtFieldPassID.setText("");
                        mainframe.txtFieldPassName.setText("");
                        hideElements();
                        op = 0;
                        setTableResults(pasDAO.getAllPassengers(),Passengerheaders, mainframe.tablePassengers );
                        
                    } else {
                        JOptionPane.showMessageDialog(null,"To create a passanger both the name and the ID must be populated");
                    }
                   
                    break;
                
                case 6:
                    pasMod.setPassengerName(mainframe.txtFieldPassName.getText());
                    if(mainframe.txtFieldPassID.getText().equals("")){
                        pasMod.setPassengerId(-1);
                    } else {
                        pasMod.setPassengerId(Long.parseLong(mainframe.txtFieldPassID.getText()));
                    }
                    setTableResults(pasDAO.getPassengersByFilter(pasMod.getPassengerId(), pasMod.getPassengerName()), Passengerheaders, mainframe.tablePassengers);
                    mainframe.txtFieldPassName.setText("");
                    mainframe.txtFieldPassID.setText("");
                    hideElements();
                    op = 0;
                    break;
                case 7:
                 
                    if(mainframe.txtFieldPassID.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Passenger ID must be populated to perform the change");
                    } else if(mainframe.txtFieldPassName.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Passenger Name must be populated to perform the change");
                    } else {
                        pasMod.setPassengerId(Long.parseLong(mainframe.txtFieldPassID.getText()));
                        pasMod.setPassengerName(mainframe.txtFieldPassName.getText());
                        pasDAO.update(pasMod.getPassengerId(), pasMod.getPassengerName());
                        mainframe.txtFieldPassID.setText("");
                        mainframe.txtFieldPassName.setText("");
                        hideElements();
                        op = 0;
                        setTableResults(pasDAO.getAllPassengers(),Passengerheaders, mainframe.tablePassengers );
                    }
                    break;
                case 8: 
                    if(!mainframe.tablePassengers.getSelectionModel().isSelectionEmpty()){
                        
                        int response = JOptionPane.showConfirmDialog(mainframe.btnGo, "Are you sure you want to delete the record?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(response == JOptionPane.YES_OPTION ){
                             pasMod.setPassengerId(Long.parseLong(mainframe.tablePassengers.getModel().getValueAt(mainframe.tablePassengers.getSelectedRow(), 0).toString()));
                             pasDAO.delete(pasMod.getPassengerId());
                             mainframe.txtFieldPassID.setText("");
                             mainframe.txtFieldPassName.setText("");
                             hideElements();
                             op = 0;
                             setTableResults(pasDAO.getAllPassengers(),Passengerheaders, mainframe.tablePassengers );
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Select one register from the table to delete it");
                    }
                    break; 
                    
                    
                //Crud actions for luggage   
                case 9:
                    if(!comboxPassIdFk.getSelectedItem().toString().equals("0")){
                        lugMod.setLuggageStatus(comboxLugStatus.getSelectedItem().toString());
                        lugMod.setPassengerIDFk(Long.parseLong(comboxPassIdFk.getSelectedItem().toString()));
                        lugDAO.insertLuggage(lugMod);
                        mainframe.txtFieldLugID.setText("");
                        comboxLugStatus.setSelectedIndex(0);
                        comboxPassIdFk.setSelectedIndex(0);
                        hideElements();
                        op = 0;
                        setTableResults(lugDAO.getAllLuggage(),Luggageheaders, mainframe.tableLuggage);
                        
                    } else {
                        JOptionPane.showMessageDialog(null,"To create luggage a Passenger ID must be selected");
                    }
                   
                    break;
                
                case 10:
                    lugMod.setLuggageStatus(comboxLugStatus.getSelectedItem().toString());
                    if(mainframe.txtFieldLugID.getText().equals("")){
                        lugMod.setLuggageID(-1);
                    } else {
                         lugMod.setLuggageID(Integer.parseInt(mainframe.txtFieldLugID.getText()));
                    }
                
                
                    if(comboxPassIdFk.getSelectedItem().toString().equals("0")) {
                       lugMod.setPassengerIDFk(-1);
                    } else {
                        lugMod.setPassengerIDFk(Long.parseLong(comboxPassIdFk.getSelectedItem().toString()));
                    }
                    setTableResults(lugDAO.getLuggageByFilter(lugMod.getLuggageID(), lugMod.getLuggageStatus(), lugMod.getPassengerIDFk()), Luggageheaders, mainframe.tableLuggage);
                    mainframe.txtFieldLugID.setText("");
                    comboxLugStatus.setSelectedIndex(0);
                    comboxPassIdFk.setSelectedIndex(0);
                    hideElements();
                    op = 0;
                    break;
                case 11:
                    
                    if (mainframe.txtFieldLugID.getText().equals("")) {
                        JOptionPane.showMessageDialog(null,"Indicate a Luggage ID to modify");
                    }
                    
                    if(!comboxLugStatus.getSelectedItem().toString().equals("ALL") && !comboxPassIdFk.getSelectedItem().toString().equals("0")){
                        lugMod.setLuggageID(Integer.parseInt(mainframe.txtFieldLugID.getText()));
                        lugMod.setLuggageStatus(comboxLugStatus.getSelectedItem().toString());
                        lugMod.setPassengerIDFk(Long.parseLong(comboxPassIdFk.getSelectedItem().toString()));
                        lugDAO.update(lugMod.getLuggageID(), lugMod.getLuggageStatus(), lugMod.getPassengerIDFk());
                        mainframe.txtFieldLugID.setText("");
                        comboxLugStatus.setSelectedIndex(0);
                        comboxPassIdFk.setSelectedIndex(0);
                        hideElements();
                        setTableResults(lugDAO.getAllLuggage(),Luggageheaders, mainframe.tableLuggage);
                    op = 0;
                    } else {
                        JOptionPane.showMessageDialog(null,"Luggage Status cannot be 'ALL' and Passenger ID cannot be 0");
                    }
                    
                    break;
                case 12: 
                    if(!mainframe.tableLuggage.getSelectionModel().isSelectionEmpty()){
                        
                        int response = JOptionPane.showConfirmDialog(mainframe.btnGo, "Are you sure you want to delete the record?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(response == JOptionPane.YES_OPTION ){
                             lugMod.setLuggageID(Integer.parseInt(mainframe.tableLuggage.getModel().getValueAt(mainframe.tableLuggage.getSelectedRow(), 0).toString()));
                             lugDAO.delete(lugMod.getLuggageID());
                              mainframe.txtFieldLugID.setText("");
                              comboxLugStatus.setSelectedIndex(0);
                              comboxPassIdFk.setSelectedIndex(0);
                              hideElements();
                              setTableResults(lugDAO.getAllLuggage(),Luggageheaders, mainframe.tableLuggage);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Select one register from the table to delete it");
                    }
                    break;  
                //Crud Actions for the Buses
                case 13:
                    if(!comboxBusSeatCap.getSelectedItem().equals("ALL")){
                        busMod.setSeatCapacity(Integer.parseInt(comboxBusSeatCap.getSelectedItem().toString()));
                        busDAO.insertBus(busMod);
                        comboxBusSeatCap.setSelectedIndex(0);
                        hideElements();
                        op = 0;
                        setTableResults(busDAO.getAllBuses(),busheaders, mainframe.tableBuses);
                        
                    } else {
                        JOptionPane.showMessageDialog(null,"To create a bus select seat capacity");
                    }
                    break;
                case 14:
                    if(mainframe.textFieldBusID.getText().equals("") && comboxBusSeatCap.getSelectedItem().equals("ALL")){
                        setTableResults(busDAO.getAllBuses(),busheaders, mainframe.tableBuses);
                        hideElements();
                    
                    } else if(mainframe.textFieldBusID.getText().equals("")){
                        busMod.setBusID(-1);
                        busMod.setSeatCapacity(Integer.parseInt(comboxBusSeatCap.getSelectedItem().toString()));
                        setTableResults(busDAO.getBusesByFilter(busMod.getBusID(), busMod.getSeatCapacity()),busheaders, mainframe.tableBuses);
                    } else if(comboxBusSeatCap.getSelectedItem().equals("ALL")){
                        busMod.setSeatCapacity(-1);
                        busMod.setBusID(Integer.parseInt(mainframe.textFieldBusID.getText()));
                        setTableResults(busDAO.getBusesByFilter(busMod.getBusID(), busMod.getSeatCapacity()),busheaders, mainframe.tableBuses);
                    } else {
                        busMod.setBusID(Integer.parseInt(mainframe.textFieldBusID.getText()));
                        busMod.setSeatCapacity(Integer.parseInt(comboxBusSeatCap.getSelectedItem().toString()));
                        setTableResults(busDAO.getBusesByFilter(busMod.getBusID(), busMod.getSeatCapacity()),busheaders, mainframe.tableBuses);
                    }
                    mainframe.textFieldBusID.setText("");
                    comboxBusSeatCap.setSelectedIndex(0);
                    hideElements();
                    op = 0;
                    break;
                case 15:
                    
                    if (mainframe.textFieldBusID.getText().equals("")) {
                        JOptionPane.showMessageDialog(null,"Indicate a Bus ID to modify");
                    }
                    
                    if(!comboxBusSeatCap.getSelectedItem().toString().equals("ALL")){
                        busMod.setBusID(Integer.parseInt(mainframe.textFieldBusID.getText()));
                        busMod.setSeatCapacity(Integer.parseInt(comboxBusSeatCap.getSelectedItem().toString()));
                        busDAO.update(busMod.getBusID(),busMod.getSeatCapacity());
 
                        mainframe.textFieldBusID.setText("");
                        comboxBusSeatCap.setSelectedIndex(0);
                        hideElements();
                        op = 0;
                        setTableResults(busDAO.getAllBuses(),busheaders, mainframe.tableBuses);
                    } else {
                        JOptionPane.showMessageDialog(null,"Seat Capacity cannot be 'ALL'");
                    }
                    
                    break;
                case 16: 
                    if(!mainframe.tableBuses.getSelectionModel().isSelectionEmpty()){
                        
                        int response = JOptionPane.showConfirmDialog(mainframe.btnGo, "Are you sure you want to delete the record?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(response == JOptionPane.YES_OPTION ){
                             busMod.setBusID(Integer.parseInt(mainframe.tableBuses.getModel().getValueAt(mainframe.tableBuses.getSelectedRow(), 0).toString()));
                             busDAO.delete(busMod.getBusID());
                             mainframe.textFieldBusID.setText("");
                             comboxBusSeatCap.setSelectedIndex(0);
                             hideElements();
                             op = 0;
                             setTableResults(busDAO.getAllBuses(),busheaders, mainframe.tableBuses);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Select one register from the table to delete it");
                    }
                    break;
                //Crud actions for the City
                case 17:
                    if(!mainframe.textFieldCityName.getText().equals("")){
                        cityMod.setCityName(mainframe.textFieldCityName.getText());
                        cityDAO.insertCity(cityMod);
                        mainframe.textFieldCityName.setText("");
                        hideElements();
                        op = 0;
                        setTableResults(cityDAO.getAllCities(),cityheaders, mainframe.tableCities);
                        
                    } else {
                        JOptionPane.showMessageDialog(null,"To create a City the City Name cannot be empty");
                    }
                   
                    break;
                
                case 18:
                    cityMod.setCityName(mainframe.textFieldCityName.getText());
                    setTableResults(cityDAO.getCitiesByFilter(cityMod.getCityName()), cityheaders, mainframe.tableCities); 
                    mainframe.textFieldCityName.setText("");
                    hideElements();
                    op = 0;
                    break;
                case 19:
                    if(mainframe.textFieldCityName.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"City Name must be populated to perform the change");
                    }  else {
                        
                        if(!mainframe.tableCities.getSelectionModel().isSelectionEmpty()){
                            
                            String currentCity = mainframe.tableCities.getModel().getValueAt(mainframe.tableCities.getSelectedRow(), 0).toString(); 
                            
                            cityMod.setCityName(mainframe.textFieldCityName.getText());
                            cityDAO.update(cityMod.getCityName(), currentCity);
                            mainframe.textFieldCityName.setText("");
                            hideElements();
                            op = 0;
                            setTableResults(cityDAO.getAllCities(),cityheaders, mainframe.tableCities);
                        } else {
                            JOptionPane.showMessageDialog(null,"Select one register from the table to modify it");
                        }
                        
                        
                    }
                    break;
                case 20: 
                    if(!mainframe.tableCities.getSelectionModel().isSelectionEmpty()){
                        
                        int response = JOptionPane.showConfirmDialog(mainframe.btnGo, "Are you sure you want to delete the record?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(response == JOptionPane.YES_OPTION ){
                             cityMod.setCityName(mainframe.tableCities.getModel().getValueAt(mainframe.tableCities.getSelectedRow(), 0).toString());
                             cityDAO.delete(cityMod.getCityName());
                             mainframe.textFieldCityName.setText("");
                             hideElements();
                             op = 0;
                             setTableResults(cityDAO.getAllCities(),cityheaders, mainframe.tableCities);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Select one register from the table to delete it");
                    }
                    break;
                //CRUD actions for Trips
                case 21:
        
                    if(!mainframe.textFieldTripsPrice.getText().equals("") && mainframe.DateChooserTripDate.getDate() != null && !comboxOriginCityFk.getSelectedItem().toString().equals("Todas las Ciudades") && !comboxDestinyCityFk.getSelectedItem().toString().equals("Todas las Ciudades") && !comboxEmpNumFk.getSelectedItem().toString().equals("0") && !comboxBusIDFk.getSelectedItem().toString().equals("0")){
                        if(comboxOriginCityFk.getSelectedItem().toString() != comboxDestinyCityFk.getSelectedItem().toString()){
                            Date current = new Date();
                            if(mainframe.DateChooserTripDate.getDate().getTime()+60000 > current.getTime()){
                                tripMod.setTripDate(new java.sql.Date(mainframe.DateChooserTripDate.getDate().getTime()));
                                System.out.println(Integer.parseInt(mainframe.textFieldTripsPrice.getText()));
                                tripMod.setPrice(Integer.parseInt(mainframe.textFieldTripsPrice.getText()));
                                tripMod.setOriginCityFk(comboxOriginCityFk.getSelectedItem().toString());
                                tripMod.setDestinyCityFk(comboxDestinyCityFk.getSelectedItem().toString());
                                tripMod.setEmployeeNumFk(Integer.parseInt(comboxEmpNumFk.getSelectedItem().toString()));
                                tripMod.setBusIDFk(Integer.parseInt(comboxBusIDFk.getSelectedItem().toString()));
                                tripDAO.insertTrip(tripMod);
                                mainframe.DateChooserTripDate.setDate(null);
                                mainframe.textFieldTripsPrice.setText("");
                                comboxOriginCityFk.setSelectedIndex(0);
                                comboxDestinyCityFk.setSelectedIndex(0);
                                comboxEmpNumFk.setSelectedIndex(0);
                                comboxBusIDFk.setSelectedIndex(0);
                                hideElements();  
                                op = 0;
                                setTableResults(tripDAO.getAllTrips(), tripheaders, mainframe.tableTrips);
                              
                            } else {
                              JOptionPane.showMessageDialog(null,"A trip cannot be created with a date in the past");
                            }  
                        } else {
                            JOptionPane.showMessageDialog(null,"Origin City and Destiny City cannot be the same");
                        }
                          
                        
                    } else {
                        JOptionPane.showMessageDialog(null,"To create a Trip all fields must be populated\n"
                                + "Make sure the date is entered with the correct format. Better use the date chooser");
                    }
                   
                    break;
                
                case 22:
                    lugMod.setLuggageStatus(comboxLugStatus.getSelectedItem().toString());
                    if(mainframe.txtFieldLugID.getText().equals("")){
                        lugMod.setLuggageID(-1);
                    } else {
                         lugMod.setLuggageID(Integer.parseInt(mainframe.txtFieldLugID.getText()));
                    }
                
                
                    if(comboxPassIdFk.getSelectedItem().toString().equals("0")) {
                       lugMod.setPassengerIDFk(-1);
                    } else {
                        lugMod.setPassengerIDFk(Long.parseLong(comboxPassIdFk.getSelectedItem().toString()));
                    }
                    setTableResults(lugDAO.getLuggageByFilter(lugMod.getLuggageID(), lugMod.getLuggageStatus(), lugMod.getPassengerIDFk()), Luggageheaders, mainframe.tableLuggage);
                    mainframe.txtFieldLugID.setText("");
                    comboxLugStatus.setSelectedIndex(0);
                    comboxPassIdFk.setSelectedIndex(0);
                    hideElements();
                    op = 0;
                    break;
                case 23:
                    
                    if (mainframe.txtFieldLugID.getText().equals("")) {
                        JOptionPane.showMessageDialog(null,"Indicate a Luggage ID to modify");
                    }
                    
                    if(!comboxLugStatus.getSelectedItem().toString().equals("ALL") && !comboxPassIdFk.getSelectedItem().toString().equals("0")){
                        lugMod.setLuggageID(Integer.parseInt(mainframe.txtFieldLugID.getText()));
                        lugMod.setLuggageStatus(comboxLugStatus.getSelectedItem().toString());
                        lugMod.setPassengerIDFk(Long.parseLong(comboxPassIdFk.getSelectedItem().toString()));
                        lugDAO.update(lugMod.getLuggageID(), lugMod.getLuggageStatus(), lugMod.getPassengerIDFk());
                        mainframe.txtFieldLugID.setText("");
                        comboxLugStatus.setSelectedIndex(0);
                        comboxPassIdFk.setSelectedIndex(0);
                        hideElements();
                        setTableResults(lugDAO.getAllLuggage(),Luggageheaders, mainframe.tableLuggage);
                    op = 0;
                    } else {
                        JOptionPane.showMessageDialog(null,"Luggage Status cannot be 'ALL' and Passenger ID cannot be 0");
                    }
                    
                    break;
                case 24: 
                    if(!mainframe.tableLuggage.getSelectionModel().isSelectionEmpty()){
                        
                        int response = JOptionPane.showConfirmDialog(mainframe.btnGo, "Are you sure you want to delete the record?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(response == JOptionPane.YES_OPTION ){
                             lugMod.setLuggageID(Integer.parseInt(mainframe.tableLuggage.getModel().getValueAt(mainframe.tableLuggage.getSelectedRow(), 0).toString()));
                             lugDAO.delete(lugMod.getLuggageID());
                              mainframe.txtFieldLugID.setText("");
                              comboxLugStatus.setSelectedIndex(0);
                              comboxPassIdFk.setSelectedIndex(0);
                              hideElements();
                              setTableResults(lugDAO.getAllLuggage(),Luggageheaders, mainframe.tableLuggage);
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
        
        mainframe.labelPasID.setVisible(false);
        mainframe.txtFieldPassID.setVisible(false);
        mainframe.labelPasName.setVisible(false);
        mainframe.txtFieldPassName.setVisible(false);
        mainframe.btnSearchSelectPas.setVisible(false);
        
        mainframe.labelLugID.setVisible(false);
        mainframe.txtFieldLugID.setVisible(false);
        mainframe.labelLugStatus.setVisible(false);
        mainframe.labelPasIDFkLug.setVisible(false);
        comboxPassIdFk.setVisible(false);
        mainframe.btnSearchSelectLug.setVisible(false);
        comboxLugStatus.setVisible(false);
        
        mainframe.labelBusID.setVisible(false);
        mainframe.textFieldBusID.setVisible(false);
        mainframe.labelBusSeatCap.setVisible(false);
        comboxBusSeatCap.setVisible(false);
        mainframe.btnSearchSelectBus.setVisible(false);
        
        mainframe.labelCitName.setVisible(false);
        mainframe.textFieldCityName.setVisible(false);
        mainframe.btnSearchSelectCit.setVisible(false);
        
        mainframe.labelTripID.setVisible(false);
        mainframe.textFieldTripID.setVisible(false);
        mainframe.labelTripPrice.setVisible(false);
        mainframe.textFieldTripsPrice.setVisible(false);
        mainframe.labelTripDate.setVisible(false);
        mainframe.DateChooserTripDate.setVisible(false);
        mainframe.labelEmpNumFkTrip.setVisible(false);
        comboxEmpNumFk.setVisible(false);
        mainframe.labelBusIDFkTrip.setVisible(false);
        comboxBusIDFk.setVisible(false);
        mainframe.labelOriCityFkTrip.setVisible(false);
        comboxOriginCityFk.setVisible(false);
        mainframe.labelDesCityFkTrip.setVisible(false);
        comboxDestinyCityFk.setVisible(false);
        mainframe.btnSearchSelectTrip.setVisible(false);
        
        
        
        
    }   
  
    
    public void setTableResults(ArrayList<SuperModel> models, String[] headers, JTable table){
            table.removeAll();
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.setColumnIdentifiers(headers);
            table.setModel(tableModel);
            for(int i=0; i<models.size(); i++){
                tableModel.addRow(models.get(i).toArray());
            }
    }
        
}
   
