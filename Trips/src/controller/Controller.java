/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Access.BusDAO;
import Access.CityDAO;
import Access.EmployeeDAO;
import Access.LookUpDAO;
import Access.LuggageDAO;
import Access.PassengerDAO;
import Access.ReservationDAO;
import Access.TripDAO;
import Model.BusModel;
import Model.CityModel;
import Model.EmployeeModel;
import Model.LuggageModel;
import Model.PassengerModel;
import Model.ReservationModel;
import Model.SuperModel;
import Model.TripModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.function.IntSupplier;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private ReservationModel resMod;
    private ReservationDAO resDAO;
    private LookUpDAO lookUpDAO;
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
    //Comboboxes in Reservations
    private JComboBox comboxPassIdFkRes;
    private JComboBox comboxTripIdFk;
    //Comboboxes in LookUp
    private JComboBox comboxTripIDLookUp;
    private JComboBox comboxTripDateLookUp;
    private JComboBox comboxOriginCityLookUp;
    private JComboBox comboxDestinyCityLookUp;
    private JComboBox comboxPriceLookUp;
    private JComboBox comboxEmployeeNumLookUp;
    private JComboBox comboxEmployeeNameLookUp;
    private JComboBox comboxReservationNumLookUp;
    private JComboBox comboxBusIdLookUp;
    private JComboBox comboxLugIdLookUp;
    private JComboBox comboxLugStatusLookUp;
    private JComboBox comboxPassNameLookUp;
    private JComboBox comboxPassNumberLookUp;
    
    
    
    
    
    
    
    public Controller(MainFrame mainframe, EmployeeModel empMod, EmployeeDAO empDAO, PassengerModel pasMod, PassengerDAO pasDAO, LuggageModel lugMod, LuggageDAO lugDAO, BusModel busMod, BusDAO busDAO, CityModel cityMod, CityDAO cityDAO, TripModel tripMod, TripDAO tripDAO, ReservationModel resMod, ReservationDAO resDAO, LookUpDAO lookUpDAO){
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
        this.resMod = resMod;
        this.resDAO = resDAO;
        this.lookUpDAO = lookUpDAO;
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
        mainframe.btnResPanel.addActionListener(this);
        mainframe.btnLookUpPanel.addActionListener(this);
        
        mainframe.btnCreate.addActionListener(this);
        mainframe.btnSearch.addActionListener(this);
        mainframe.btnUpdate.addActionListener(this);
        mainframe.btnSearchSelectEmp.addActionListener(this);
        mainframe.btnSearchSelectPas.addActionListener(this);
        mainframe.btnSearchSelectLug.addActionListener(this);
        mainframe.btnSearchSelectBus.addActionListener(this);
        mainframe.btnSearchSelectCit.addActionListener(this);
        mainframe.btnSearchSelectTrip.addActionListener(this);
        mainframe.btnSearchSelectRes.addActionListener(this);
        mainframe.btnDelete.addActionListener(this);
        mainframe.btnGo.addActionListener(this);
        mainframe.btnClearFields.addActionListener(this);
        mainframe.btnResetLookUp.addActionListener(this);
        mainframe.btnSearchLookUp.addActionListener(this);
        
        
        InitialDataComboBoxes initialData = new InitialDataComboBoxes();
        
        //Creación combobox en Luggage para seleción de Status
        String[] lugStatusOptions = {"ALL", "OK", "LOST"};
        this.comboxLugStatus = new JComboBox(lugStatusOptions);
        this.comboxLugStatus.setBounds(460, 125, 100, 30);
        mainframe.LugPane.add(this.comboxLugStatus);
        
        
        //Creación combobox en Luggage para selección de PassenderIdFk
        this.comboxPassIdFk = new JComboBox();        
        this.comboxPassIdFk.setModel(new DefaultComboBoxModel<>(initialData.getPassengers().toArray(new SuperModel[initialData.getPassengers().size()])));
        this.comboxPassIdFk.setSelectedIndex(0);
        this.comboxPassIdFk.setBounds(460, 180, 200, 30);
        mainframe.LugPane.add(this.comboxPassIdFk);     
        
        //Creación combobox en Buses para selección capacidad
        Object[] busSeatCapOptions = {"ALL",30,40,45,90};
        this.comboxBusSeatCap = new JComboBox(busSeatCapOptions);
        this.comboxBusSeatCap.setBounds(590, 130, 100, 30);
        mainframe.BusPane.add(this.comboxBusSeatCap);
        
        
        //Creación combobox en Trips para selección de employeeNumFk
        this.comboxEmpNumFk = new JComboBox();
        this.comboxEmpNumFk.setModel(new DefaultComboBoxModel<>(initialData.getEmployees().toArray(new SuperModel[initialData.getEmployees().size()])));
        this.comboxEmpNumFk.setSelectedIndex(0);
        this.comboxEmpNumFk.setBounds(650, 140, 60, 30);
        mainframe.TripPane.add(this.comboxEmpNumFk);
        
        //Creación combobox en Trips para selección de originCityFk
        this.comboxOriginCityFk = new JComboBox();
        this.comboxOriginCityFk.setModel(new DefaultComboBoxModel<>(initialData.getCities().toArray(new SuperModel[initialData.getCities().size()])));
        this.comboxOriginCityFk.setSelectedIndex(0);
        this.comboxOriginCityFk.setBounds(650, 65, 140, 30);
        mainframe.TripPane.add(this.comboxOriginCityFk);
        
        //Creación combobox en Trips para selección de destinyCityFk
        this.comboxDestinyCityFk = new JComboBox();
        this.comboxDestinyCityFk.setModel(new DefaultComboBoxModel<>(initialData.getCities().toArray(new SuperModel[initialData.getCities().size()])));
        this.comboxDestinyCityFk.setSelectedIndex(0);
        this.comboxDestinyCityFk.setBounds(650, 102, 140, 30);
        mainframe.TripPane.add(this.comboxDestinyCityFk);
        
        
        //Creación combobox en Trips para selección de busIDFK;
        this.comboxBusIDFk = new JComboBox();
        this.comboxBusIDFk.setModel(new DefaultComboBoxModel<>(initialData.getBuses().toArray(new SuperModel[initialData.getBuses().size()])));
        this.comboxBusIDFk.setSelectedIndex(0);
        this.comboxBusIDFk.setBounds(650, 174, 60, 30);
        mainframe.TripPane.add(this.comboxBusIDFk);
        
        
        //Combobox creation in Reservations for passengerIDFk selection
        this.comboxPassIdFkRes = new JComboBox();
        this.comboxPassIdFkRes.setModel(new DefaultComboBoxModel<>(initialData.getPassengers().toArray(new SuperModel[initialData.getPassengers().size()])));
        this.comboxPassIdFkRes.setSelectedIndex(0);
        this.comboxPassIdFkRes.setBounds(534, 110, 100, 30);
        mainframe.ResPane.add(this.comboxPassIdFkRes);
        
        //Combobox creation in Reservations for tripIDFk selection
        this.comboxTripIdFk = new JComboBox();
        this.comboxTripIdFk.setModel(new DefaultComboBoxModel<>(initialData.getTrips().toArray(new SuperModel[initialData.getTrips().size()])));
        this.comboxTripIdFk.setSelectedIndex(0);
        this.comboxTripIdFk.setBounds(534, 150, 60, 30);
        mainframe.ResPane.add(this.comboxTripIdFk);
        

        //Combobox creation in LookUp
        this.comboxTripIDLookUp = new JComboBox();
        this.comboxTripDateLookUp = new JComboBox();
        this.comboxOriginCityLookUp = new JComboBox();
        this.comboxDestinyCityLookUp = new JComboBox();
        this.comboxPriceLookUp = new JComboBox();
        this.comboxEmployeeNumLookUp = new JComboBox();
        this.comboxEmployeeNameLookUp = new JComboBox();
        this.comboxReservationNumLookUp = new JComboBox();
        this.comboxBusIdLookUp = new JComboBox();
        this.comboxLugIdLookUp = new JComboBox();
        this.comboxLugStatusLookUp = new JComboBox();
        this.comboxPassNameLookUp = new JComboBox();
        this.comboxPassNumberLookUp = new JComboBox();
        
        
        //Positioning of Comboboxes in LookUp
        this.comboxTripIDLookUp.setBounds(167, 63, 60, 25);
        mainframe.LookUpPane.add(this.comboxTripIDLookUp);

        this.comboxTripDateLookUp.setBounds(167, 95, 95, 25);
        mainframe.LookUpPane.add(this.comboxTripDateLookUp);
        
        this.comboxOriginCityLookUp.setBounds(167, 130, 90, 25);
        mainframe.LookUpPane.add(this.comboxOriginCityLookUp);
        
        this.comboxDestinyCityLookUp.setBounds(167, 163, 90, 25);
        mainframe.LookUpPane.add(this.comboxDestinyCityLookUp);
        
        this.comboxPriceLookUp.setBounds(387, 63, 90, 25);
        mainframe.LookUpPane.add(this.comboxPriceLookUp);
        
        this.comboxEmployeeNumLookUp.setBounds(387, 98, 90, 25);
        mainframe.LookUpPane.add(this.comboxEmployeeNumLookUp);
        
        this.comboxEmployeeNameLookUp.setBounds(387, 133, 90, 25);
        mainframe.LookUpPane.add(this.comboxEmployeeNameLookUp);
        
        this.comboxReservationNumLookUp.setBounds(387, 164, 90, 25);
        mainframe.LookUpPane.add(this.comboxReservationNumLookUp);
        
        this.comboxBusIdLookUp.setBounds(597, 65, 60, 25);
        mainframe.LookUpPane.add(this.comboxBusIdLookUp);
        
        this.comboxLugIdLookUp.setBounds(597, 110, 60, 25);
        mainframe.LookUpPane.add(this.comboxLugIdLookUp);
        
        this.comboxLugStatusLookUp.setBounds(597, 162, 60, 25);
        mainframe.LookUpPane.add(this.comboxLugStatusLookUp);
        
        this.comboxPassNameLookUp.setBounds(837, 162, 127, 25);
        mainframe.LookUpPane.add(this.comboxPassNameLookUp);
        
        this.comboxPassNumberLookUp.setBounds(837, 108, 107, 25);
        mainframe.LookUpPane.add(this.comboxPassNumberLookUp);
        
        
//        //Combobox creation in LookUp for TripID
//        Integer[] trpIDs = new Integer[initialData.getDataList().size()];   
//        for(int i = 0; i<initialData.getDataList().size();i++){
//            if(i==0){
//                trpIDs[0] = ((TripModel)initialData.getDataList().get(i)).getTripID();
//            } else if (i>0){
//                   for(int j = 0; j<=i;j++) {
//                        if(trpIDs[j] != null){
//                            if(trpIDs[j] == ((TripModel)initialData.getDataList().get(i)).getTripID()){
//                                break;
//                            } 
//                        } else {
//                            if(trpIDs[j-1] == null || trpIDs[j-1] == ((TripModel)initialData.getDataList().get(i)).getTripID()){
//                                trpIDs[j] = null;
//                            } else {
//                                trpIDs[j] = ((TripModel)initialData.getDataList().get(i)).getTripID();
//                            }
//                        }
//                   }    
//            }
//        }
//        trpIDs = resizeSortIntegerArray(trpIDs);
//        this.comboxTripIDLookUp = new JComboBox(trpIDs);
//        this.comboxTripIDLookUp.setSelectedIndex(0);
//        this.comboxTripIDLookUp.setBounds(167, 63, 60, 25);
//        mainframe.LookUpPane.add(this.comboxTripIDLookUp);
//        
//        
//        
//
//               
//        //Combobox creation in LookUp for TripDate
//        Date[] trpDate = new Date[initialData.getDataList().size()];  
//        for(int i = 0; i<initialData.getDataList().size();i++){
//            if(i==0){
//                trpDate[0] = ((TripModel)initialData.getDataList().get(i)).getTripDate();
//            } else if (i>0){
//                   for(int j = 0; j<=i;j++) {
//                        if(trpDate[j] != null){
//                            if(trpDate[j].compareTo(((TripModel)initialData.getDataList().get(i)).getTripDate()) == 0){
//                                break;
//                            } 
//                        } else {
//                            if(trpDate[j-1] == null || trpDate[j-1].compareTo(((TripModel)initialData.getDataList().get(i)).getTripDate()) == 0){
//                                trpDate[j] = null;
//                            } else {
//                                trpDate[j] = ((TripModel)initialData.getDataList().get(i)).getTripDate();
//                            }
//                        }
//                   }    
//            }
//        }
//        trpDate = resizeSortDateArray(trpDate);
//        trpDate[0] = null;
//        this.comboxTripDateLookUp = new JComboBox(trpDate);
//        this.comboxTripDateLookUp.setSelectedIndex(0);
//        this.comboxTripDateLookUp.setBounds(167, 95, 95, 25);
//        mainframe.LookUpPane.add(this.comboxTripDateLookUp);
//        
//        
//        
//        
//        //Combobox creation in LookUp for OriginCity
//        String[] originCities = new String[initialData.getDataList().size()];  
//        for(int i = 0; i<initialData.getDataList().size();i++){
//            if(i==0){
//                originCities[0] = ((TripModel)initialData.getDataList().get(i)).getOriginCityFk();
//            } else if (i>0){
//                   for(int j = 0; j<=i;j++) {
//                        if(originCities[j] != null){
//                            if(originCities[j].equals(((TripModel)initialData.getDataList().get(i)).getOriginCityFk())){
//                                break;
//                            } 
//                        } else {
//                            if(originCities[j-1] == null || originCities[j-1].equals(((TripModel)initialData.getDataList().get(i)).getOriginCityFk())){
//                                originCities[j] = null;
//                            } else {
//                                originCities[j] = ((TripModel)initialData.getDataList().get(i)).getOriginCityFk();
//                            }
//                        }
//                   }    
//            }
//        }
//        originCities = resizeSortStringArray(originCities);
//        this.comboxOriginCityLookUp = new JComboBox(originCities);
//        this.comboxOriginCityLookUp.setSelectedIndex(0);
//        this.comboxOriginCityLookUp.setBounds(167, 130, 90, 25);
//        mainframe.LookUpPane.add(this.comboxOriginCityLookUp);
//     
//        
//        
//        
//        //Combobox creation in LookUp for DestinyCity
//        String[] destinyCities = new String[initialData.getDataList().size()];  
//        for(int i = 0; i<initialData.getDataList().size();i++){
//            if(i==0){
//                destinyCities[0] = ((TripModel)initialData.getDataList().get(i)).getDestinyCityFk();
//            } else if (i>0){
//                   for(int j = 0; j<=i;j++) {
//                        if(destinyCities[j] != null){
//                            if(destinyCities[j].equals(((TripModel)initialData.getDataList().get(i)).getDestinyCityFk())){
//                                break;
//                            } 
//                        } else {
//                            if(destinyCities[j-1] == null || destinyCities[j-1].equals(((TripModel)initialData.getDataList().get(i)).getDestinyCityFk())){
//                                destinyCities[j] = null;
//                            } else {
//                                destinyCities[j] = ((TripModel)initialData.getDataList().get(i)).getDestinyCityFk();
//                            }
//                        }
//                   }    
//            }
//        }
//        destinyCities = resizeSortStringArray(destinyCities);
//        this.comboxDestinyCityLookUp = new JComboBox(destinyCities);
//        this.comboxDestinyCityLookUp.setSelectedIndex(0);
//        this.comboxDestinyCityLookUp.setBounds(167, 163, 90, 25);
//        mainframe.LookUpPane.add(this.comboxDestinyCityLookUp);
//        
//        
//        
//        
//        
//        //Combobox creation in LookUp for Price
//        Integer[] prices = new Integer[initialData.getDataList().size()];
//            for(int i = 0; i<initialData.getDataList().size();i++){
//                if(i==0){
//                    prices[0] = ((TripModel)initialData.getDataList().get(i)).getPrice();
//                } else if (i>0){
//                       for(int j = 0; j<=i;j++) {
//                            if(prices[j] != null){
//                                if(prices[j] == ((TripModel)initialData.getDataList().get(i)).getPrice()){
//                                    break;
//                                } 
//                            } else {
//                                if(prices[j-1] == null || prices[j-1] == ((TripModel)initialData.getDataList().get(i)).getPrice()){
//                                    prices[j] = null;
//                                } else {
//                                    prices[j] = ((TripModel)initialData.getDataList().get(i)).getPrice();
//                                }
//                            }
//                       }    
//                }
//            }
//        prices = resizeSortIntegerArray(prices);
//        this.comboxPriceLookUp = new JComboBox(prices);
//        this.comboxPriceLookUp.setSelectedIndex(0);
//        this.comboxPriceLookUp.setBounds(387, 63, 90, 25);
//        mainframe.LookUpPane.add(this.comboxPriceLookUp);
//        
//        
//        
//        
//        //Combobox creation in LookUp for EmpNum
//        Integer[] empNums = new Integer[initialData.getDataList().size()];   
//        for(int i = 0; i<initialData.getDataList().size();i++){
//            if(i==0){
//                empNums[0] = ((TripModel)initialData.getDataList().get(i)).getEmployeeNumFk();
//            } else if (i>0){
//                   for(int j = 0; j<=i;j++) {
//                        if(empNums[j] != null){
//                            if(trpIDs[j] == ((TripModel)initialData.getDataList().get(i)).getEmployeeNumFk()){
//                                break;
//                            } 
//                        } else {
//                            if(empNums[j-1] == null || empNums[j-1] == ((TripModel)initialData.getDataList().get(i)).getEmployeeNumFk()){
//                                empNums[j] = null;
//                            } else {
//                                empNums[j] = ((TripModel)initialData.getDataList().get(i)).getEmployeeNumFk();
//                            }
//                        }
//                   }    
//            }
//        }
//        empNums = resizeSortIntegerArray(empNums);
//        this.comboxEmployeeNumLookUp = new JComboBox(empNums);
//        this.comboxEmployeeNumLookUp.setSelectedIndex(0);
//        this.comboxEmployeeNumLookUp.setBounds(387, 98, 90, 25);
//        mainframe.LookUpPane.add(this.comboxEmployeeNumLookUp);
//        
//        
//        
//        
//        
//        
//        //Combobox creation in LookUp for EmpName
//        String[] empNames = new String[initialData.getDataList().size()];  
//        for(int i = 0; i<initialData.getDataList().size();i++){
//            if(i==0){
//                empNames[0] = ((TripModel)initialData.getDataList().get(i)).getEmployeeNameLU();
//            } else if (i>0){
//                   for(int j = 0; j<=i;j++) {
//                        if(empNames[j] != null){
//                            if(empNames[j].equals(((TripModel)initialData.getDataList().get(i)).getEmployeeNameLU())){
//                                break;
//                            } 
//                        } else {
//                            if(empNames[j-1] == null || empNames[j-1].equals(((TripModel)initialData.getDataList().get(i)).getEmployeeNameLU())){
//                                empNames[j] = null;
//                            } else {
//                                empNames[j] = ((TripModel)initialData.getDataList().get(i)).getEmployeeNameLU();
//                            }
//                        }
//                   }    
//            }
//        }
//        empNames = resizeSortStringArray(empNames);
//        this.comboxEmployeeNameLookUp = new JComboBox(empNames);
//        this.comboxEmployeeNameLookUp.setSelectedIndex(0);
//        this.comboxEmployeeNameLookUp.setBounds(387, 133, 90, 25);
//        mainframe.LookUpPane.add(this.comboxEmployeeNameLookUp);
//
//
//
//        
//        
//        //Combobox creation in LookUp for ResNum 
//        Integer[] resNums = new Integer[initialData.getDataList().size()]; 
//        for(int i = 0; i<initialData.getDataList().size();i++){
//            if(i==0){
//                resNums[0] = ((TripModel)initialData.getDataList().get(i)).getReservationNumLU();
//            } else if (i>0){
//                   for(int j = 0; j<=i;j++) {
//                        if(resNums[j] != null){
//                            if(resNums[j] == ((TripModel)initialData.getDataList().get(i)).getReservationNumLU()){
//                                break;
//                            } 
//                        } else {
//                            if(resNums[j-1] == null || resNums[j-1] == ((TripModel)initialData.getDataList().get(i)).getReservationNumLU()){
//                                resNums[j] = null;
//                            } else {
//                                resNums[j] = ((TripModel)initialData.getDataList().get(i)).getReservationNumLU();
//                            }
//                        }
//                   }    
//            }
//        }
//        resNums = resizeSortIntegerArray(resNums);
//        this.comboxReservationNumLookUp = new JComboBox(resNums);
//        this.comboxReservationNumLookUp.setSelectedIndex(0);
//        this.comboxReservationNumLookUp.setBounds(387, 164, 90, 25);
//        mainframe.LookUpPane.add(this.comboxReservationNumLookUp);
//        
//        
//        
//        
//        //Combobox creation in LookUp for BusID
//        Integer[] BusIds = new Integer[initialData.getDataList().size()];
//            for(int i = 0; i<initialData.getDataList().size();i++){
//                if(i==0){
//                    BusIds[0] = ((TripModel)initialData.getDataList().get(i)).getBusIDFk();
//                } else if (i>0){
//                       for(int j = 0; j<=i;j++) {
//                            if(BusIds[j] != null){
//                                if(BusIds[j] == ((TripModel)initialData.getDataList().get(i)).getBusIDFk()){
//                                    break;
//                                } 
//                            } else {
//                                if(BusIds[j-1] == null || BusIds[j-1] == ((TripModel)initialData.getDataList().get(i)).getBusIDFk()){
//                                    BusIds[j] = null;
//                                } else {
//                                    BusIds[j] = ((TripModel)initialData.getDataList().get(i)).getBusIDFk();
//                                }
//                            }
//                       }    
//                }
//            }
//        BusIds = resizeSortIntegerArray(BusIds);
//        this.comboxBusIdLookUp = new JComboBox(BusIds);
//        this.comboxBusIdLookUp.setSelectedIndex(0);
//        this.comboxBusIdLookUp.setBounds(597, 65, 60, 25);
//        mainframe.LookUpPane.add(this.comboxBusIdLookUp);
//
//
//        
//        //Combobox creation in LookUp for luggageIDLU
//        Integer[] lugIds = new Integer[initialData.getDataList().size()];
//            for(int i = 0; i<initialData.getDataList().size();i++){
//                if(i==0){
//                    lugIds[0] = ((TripModel)initialData.getDataList().get(i)).getLuggageIDLU();
//                } else if (i>0){
//                       for(int j = 0; j<=i;j++) {
//                            if(lugIds[j] != null){
//                                if(lugIds[j] == ((TripModel)initialData.getDataList().get(i)).getLuggageIDLU()){
//                                    break;
//                                } 
//                            } else {
//                                if(lugIds[j-1] == null || lugIds[j-1] == ((TripModel)initialData.getDataList().get(i)).getLuggageIDLU()){
//                                    lugIds[j] = null;
//                                } else {
//                                    lugIds[j] = ((TripModel)initialData.getDataList().get(i)).getLuggageIDLU();
//                                }
//                            }
//                       }    
//                }
//            }
//        lugIds = resizeSortIntegerArray(lugIds);
//        this.comboxLugIdLookUp = new JComboBox(lugIds);
//        this.comboxLugIdLookUp.setSelectedIndex(0);
//        this.comboxLugIdLookUp.setBounds(597, 110, 60, 25);
//        mainframe.LookUpPane.add(this.comboxLugIdLookUp);
//        
//        
//        
//        
//        //Combobox creation in LookUp for LugStatus
//        String[] lugStatus = new String[initialData.getDataList().size()];  
//        for(int i = 0; i<initialData.getDataList().size();i++){
//            if(i==0){
//                lugStatus[0] = ((TripModel)initialData.getDataList().get(i)).getLuggageStatusLU();
//            } else if (i>0){
//                   for(int j = 0; j<=i;j++) {
//                        if(lugStatus[j] != null){
//                            if(lugStatus[j].equals(((TripModel)initialData.getDataList().get(i)).getLuggageStatusLU())){
//                                break;
//                            } 
//                        } else {
//                            if(lugStatus[j-1] == null || lugStatus[j-1].equals(((TripModel)initialData.getDataList().get(i)).getLuggageStatusLU())){
//                                lugStatus[j] = null;
//                            } else {
//                                lugStatus[j] = ((TripModel)initialData.getDataList().get(i)).getLuggageStatusLU();
//                            }
//                        }
//                   }    
//            }
//        }
//        lugStatus = resizeSortStringArray(lugStatus);
//        this.comboxLugStatusLookUp = new JComboBox(lugStatus);
//        this.comboxLugStatusLookUp.setSelectedIndex(0);
//        this.comboxLugStatusLookUp.setBounds(597, 162, 60, 25);
//        mainframe.LookUpPane.add(this.comboxLugStatusLookUp);
//        
//        
//        //Combobox creation in LookUp for PassName
//        String[] pasNames = new String[initialData.getDataList().size()];  
//        for(int i = 0; i<initialData.getDataList().size();i++){
//            if(i==0){
//                pasNames[0] = ((TripModel)initialData.getDataList().get(i)).getPassengerNameLU();
//            } else if (i>0){
//                   for(int j = 0; j<=i;j++) {
//                        if(pasNames[j] != null){
//                            if(pasNames[j].equals(((TripModel)initialData.getDataList().get(i)).getPassengerNameLU())){
//                                break;
//                            } 
//                        } else {
//                            if(pasNames[j-1] == null || pasNames[j-1].equals(((TripModel)initialData.getDataList().get(i)).getPassengerNameLU())){
//                                pasNames[j] = null;
//                            } else {
//                                pasNames[j] = ((TripModel)initialData.getDataList().get(i)).getPassengerNameLU();
//                            }
//                        }
//                   }    
//            }
//        }
//        pasNames = resizeSortStringArray(pasNames);
//        this.comboxPassNameLookUp = new JComboBox(pasNames);
//        this.comboxPassNameLookUp.setSelectedIndex(0);
//        this.comboxPassNameLookUp.setBounds(837, 162, 127, 25);
//        mainframe.LookUpPane.add(this.comboxPassNameLookUp);
//        
//        
//        
//        
//        //Combobox creation in LookUp for passengerIDLU
//        Long[] passIds = new Long[initialData.getDataList().size()];
//            for(int i = 0; i<initialData.getDataList().size();i++){
//                if(i==0){
//                    passIds[0] = ((TripModel)initialData.getDataList().get(i)).getPassengerIDLU();
//                } else if (i>0){
//                       for(int j = 0; j<=i;j++) {
//                            if(passIds[j] != null){
//                                if(passIds[j] == ((TripModel)initialData.getDataList().get(i)).getPassengerIDLU()){
//                                    break;
//                                } 
//                            } else {
//                                if(passIds[j-1] == null || passIds[j-1] == ((TripModel)initialData.getDataList().get(i)).getPassengerIDLU()){
//                                    passIds[j] = null;
//                                } else {
//                                    passIds[j] = ((TripModel)initialData.getDataList().get(i)).getPassengerIDLU();
//                                }
//                            }
//                       }    
//                }
//            }
//        passIds = resizeSortLongArray(passIds);
//        this.comboxPassNumberLookUp = new JComboBox(passIds);
//        this.comboxPassNumberLookUp.setSelectedIndex(0);
//        this.comboxPassNumberLookUp.setBounds(837, 108, 107, 25);
//        mainframe.LookUpPane.add(this.comboxPassNumberLookUp);
        
        
        
        
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
        String[] reservationheaders = {"Reservation Number","Passenger ID","Trip ID"};
        String[] lookupheaders = {"Trip ID","Trip Date","Orig City","Dest City","Price","Emp Num","Emp Name","Res Num","Bus ID","Lug ID","Lug Status","Pass ID","Pass Name"};
        
        
        if(e.getSource() == mainframe.btnEmpPanel){
            mainframe.crudPane.setVisible(true);
            hideElements();
            mainframe.dataDisplayPane.removeAll();
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            setTableResults(empDAO.getAllEmployees(), Employeeheaders,mainframe.tableEmployees);
            mainframe.dataDisplayPane.add(mainframe.EmpPane);
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            currEntity = 1;
        } else if(e.getSource() == mainframe.btnPasPanel){
            mainframe.crudPane.setVisible(true);
            hideElements();
            mainframe.dataDisplayPane.removeAll();
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            setTableResults(pasDAO.getAllPassengers(),Passengerheaders, mainframe.tablePassengers );
            mainframe.dataDisplayPane.add(mainframe.PasPane);
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            currEntity = 2;
        } else if(e.getSource() == mainframe.btnLugPanel){
            mainframe.crudPane.setVisible(true);
            hideElements();
            mainframe.dataDisplayPane.removeAll();
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            setTableResults(lugDAO.getAllLuggage(),Luggageheaders, mainframe.tableLuggage);
            mainframe.dataDisplayPane.add(mainframe.LugPane);
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            currEntity = 3;
            resetComboboxesLuggage();
            
        } else if(e.getSource() == mainframe.btnBusPanel){
            mainframe.crudPane.setVisible(true);
            hideElements();
            mainframe.dataDisplayPane.removeAll();
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            setTableResults(busDAO.getAllBuses(),busheaders, mainframe.tableBuses);
            mainframe.dataDisplayPane.add(mainframe.BusPane);
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            currEntity = 4;   
            
        } else if(e.getSource() == mainframe.btnCitPanel){
            mainframe.crudPane.setVisible(true);
            hideElements();
            mainframe.dataDisplayPane.removeAll();
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            setTableResults(cityDAO.getAllCities(),cityheaders, mainframe.tableCities);
            mainframe.dataDisplayPane.add(mainframe.CitPane);
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            currEntity = 5;
            
        } else if(e.getSource() == mainframe.btnTripPanel){
            mainframe.crudPane.setVisible(true);
            hideElements();
            mainframe.dataDisplayPane.removeAll();
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            setTableResults(tripDAO.getAllTrips(), tripheaders, mainframe.tableTrips);
            mainframe.dataDisplayPane.add(mainframe.TripPane);
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            currEntity = 6;
            resetComboboxesTrips();
        } else if(e.getSource() == mainframe.btnResPanel){
            mainframe.crudPane.setVisible(true);
            hideElements();
            mainframe.dataDisplayPane.removeAll();
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            setTableResults(resDAO.getAllReservations(), reservationheaders, mainframe.tableReservations);
            mainframe.dataDisplayPane.add(mainframe.ResPane);
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            currEntity = 7;
            resetComboboxesReservations();
        } else if(e.getSource() == mainframe.btnLookUpPanel){
            mainframe.crudPane.setVisible(false);
            hideElements();
            mainframe.dataDisplayPane.removeAll();
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            currEntity = 8;
            setTableResults(lookUpDAO.getAllData(), lookupheaders, mainframe.tableLookUp);
            mainframe.dataDisplayPane.add(mainframe.LookUpPane);
            mainframe.dataDisplayPane.revalidate();
            mainframe.dataDisplayPane.repaint();
            setComboboxesLookUp(this.comboxTripIDLookUp, this.comboxTripDateLookUp, this.comboxOriginCityLookUp, this.comboxDestinyCityLookUp, this.comboxPriceLookUp, this.comboxEmployeeNumLookUp, this.comboxEmployeeNameLookUp, this.comboxReservationNumLookUp, this.comboxBusIdLookUp, this.comboxLugIdLookUp, this.comboxLugStatusLookUp, this.comboxPassNameLookUp, this.comboxPassNumberLookUp);
            resetCombosLookUp();
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
            case 7: 
                if(e.getSource() == mainframe.btnCreate){
                    hideElements();
                    mainframe.labelPassIDFkRes.setVisible(true);
                    comboxPassIdFkRes.setVisible(true);
                    mainframe.labelTripIDFkRes.setVisible(true);
                    comboxTripIdFk.setVisible(true);
                    op = 25;
                } else if(e.getSource() == mainframe.btnSearch){
                    hideElements();
                    mainframe.labelResNum.setVisible(true);
                    mainframe.textFieldResNum.setVisible(true);
                    mainframe.labelPassIDFkRes.setVisible(true);
                    comboxPassIdFkRes.setVisible(true);
                    mainframe.labelTripIDFkRes.setVisible(true);
                    comboxTripIdFk.setVisible(true); 
                    op = 26;
                } else if(e.getSource() == mainframe.btnUpdate){
                    hideElements();
                    mainframe.labelResNum.setVisible(true);
                    mainframe.textFieldResNum.setVisible(true);
                    mainframe.labelPassIDFkRes.setVisible(true);
                    comboxPassIdFkRes.setVisible(true);
                    mainframe.labelTripIDFkRes.setVisible(true);
                    comboxTripIdFk.setVisible(true);
                    mainframe.btnSearchSelectRes.setVisible(true); 
                    op = 27;
                } else if(e.getSource() == mainframe.btnDelete){
                    hideElements();
                    mainframe.labelResNum.setVisible(true);
                    mainframe.textFieldResNum.setVisible(true);
                    mainframe.labelPassIDFkRes.setVisible(true);
                    comboxPassIdFkRes.setVisible(true);
                    mainframe.labelTripIDFkRes.setVisible(true);
                    comboxTripIdFk.setVisible(true);
                    mainframe.btnSearchSelectRes.setVisible(true); 
                    op = 28;

                } else if (e.getSource() == mainframe.btnClearFields){
                    mainframe.textFieldResNum.setText("");
                    comboxPassIdFkRes.setSelectedIndex(0);
                    comboxTripIdFk.setSelectedIndex(0);
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
       
       
       if(e.getSource() == mainframe.btnSearchSelectTrip){
           if(op == 23 || op == 24){
               if(!mainframe.tableTrips.getSelectionModel().isSelectionEmpty()){
                   
                   mainframe.textFieldTripID.setText(mainframe.tableTrips.getModel().getValueAt(mainframe.tableTrips.getSelectedRow(), 0).toString());
                   
                   //Parses the date in String form from the table to a Date for the DateChooser 
                   try {
                       mainframe.DateChooserTripDate.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(mainframe.tableTrips.getModel().getValueAt(mainframe.tableTrips.getSelectedRow(), 1).toString()));
                   } catch (ParseException ex) {
                       Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   
                   mainframe.textFieldTripsPrice.setText(mainframe.tableTrips.getModel().getValueAt(mainframe.tableTrips.getSelectedRow(), 2).toString());
                   
                   //Gets the selected value from the table, then gets the amount of items in the combobox and loops through them comparing them to the selected value,
                   //when there is a match sets the selected index to the current iteration.
                   setComboboxIndexFromTableSelection(mainframe.tableTrips.getModel().getValueAt(mainframe.tableTrips.getSelectedRow(), 3).toString(), comboxOriginCityFk);
                   setComboboxIndexFromTableSelection(mainframe.tableTrips.getModel().getValueAt(mainframe.tableTrips.getSelectedRow(), 4).toString(), comboxDestinyCityFk);
                   setComboboxIndexFromTableSelection(mainframe.tableTrips.getModel().getValueAt(mainframe.tableTrips.getSelectedRow(), 5).toString(), comboxEmpNumFk);
                   setComboboxIndexFromTableSelection(mainframe.tableTrips.getModel().getValueAt(mainframe.tableTrips.getSelectedRow(), 6).toString(), comboxBusIDFk);
                }
           }
           
           if(!mainframe.textFieldTripID.getText().equals("") || !mainframe.textFieldTripsPrice.getText().equals("") || mainframe.DateChooserTripDate.getDate() != null || !comboxOriginCityFk.getSelectedItem().toString().equals("Todas las Ciudades") || !comboxDestinyCityFk.getSelectedItem().toString().equals("Todas las Ciudades") || !comboxEmpNumFk.getSelectedItem().toString().equals("0") || !comboxBusIDFk.getSelectedItem().toString().equals("0")){
                        
                        
                if(mainframe.textFieldTripID.getText().equals("")){
                    tripMod.setTripID(-1);
                } else {
                    tripMod.setTripID(Integer.parseInt(mainframe.textFieldTripID.getText()));
                }



                if(mainframe.DateChooserTripDate.getDate() == null){
                    tripMod.setTripDate(null);
                } else {
                    tripMod.setTripDate(new java.sql.Date(mainframe.DateChooserTripDate.getDate().getTime()));
                }

                if(mainframe.textFieldTripsPrice.getText().equals("")){
                    tripMod.setPrice(-1);
                } else {
                   tripMod.setPrice(Integer.parseInt(mainframe.textFieldTripsPrice.getText()));
                }

                tripMod.setOriginCityFk(comboxOriginCityFk.getSelectedItem().toString());
                tripMod.setDestinyCityFk(comboxDestinyCityFk.getSelectedItem().toString());
                tripMod.setEmployeeNumFk(Integer.parseInt(comboxEmpNumFk.getSelectedItem().toString()));
                tripMod.setBusIDFk(Integer.parseInt(comboxBusIDFk.getSelectedItem().toString()));
                setTableResults(tripDAO.getTripsByFilter(tripMod), tripheaders, mainframe.tableTrips);

            } else {
                setTableResults(tripDAO.getAllTrips(), tripheaders, mainframe.tableTrips);
            }
           
       }
       
       
       
       
       if(e.getSource() == mainframe.btnSearchSelectRes){
           if(op == 27 || op == 28){
               if(!mainframe.tableReservations.getSelectionModel().isSelectionEmpty()){
                   
                   mainframe.textFieldResNum.setText(mainframe.tableReservations.getModel().getValueAt(mainframe.tableReservations.getSelectedRow(), 0).toString());
                   
                   //Gets the selected value from the table, then gets the amount of items in the combobox and loops through them comparing them to the selected value,
                   //when there is a match sets the selected index to the current iteration.
                   setComboboxIndexFromTableSelection(mainframe.tableReservations.getModel().getValueAt(mainframe.tableReservations.getSelectedRow(), 1).toString(), comboxPassIdFkRes);
                   setComboboxIndexFromTableSelection(mainframe.tableReservations.getModel().getValueAt(mainframe.tableReservations.getSelectedRow(), 2).toString(), comboxTripIdFk);
                }
           }
           
           
           
           if(!mainframe.textFieldResNum.getText().equals("") || !comboxPassIdFkRes.getSelectedItem().toString().equals("0") || !comboxTripIdFk.getSelectedItem().toString().equals("0")){      
                if(mainframe.textFieldResNum.getText().equals("")){
                    resMod.setReservationNum(-1);
                } else {
                    resMod.setReservationNum(Integer.parseInt(mainframe.textFieldResNum.getText()));
                }

                resMod.setPassengerIDFk(Long.parseLong(comboxPassIdFkRes.getSelectedItem().toString()));
                resMod.setTripIDFk(Integer.parseInt(comboxTripIdFk.getSelectedItem().toString()));
                
                setTableResults(resDAO.getReservationsByFilter(resMod), reservationheaders, mainframe.tableReservations);
                        
            } else {
                setTableResults(resDAO.getAllReservations(), reservationheaders, mainframe.tableReservations);
            }
           
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
                    
                    if (!mainframe.txtFieldLugID.getText().equals("")) {
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
                    } else {
                        JOptionPane.showMessageDialog(null,"Indicate a Luggage ID to modify");
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
                              op = 0;
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
                    
                    if (!mainframe.textFieldBusID.getText().equals("")) {
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
                        
                        
                    } else {
                        JOptionPane.showMessageDialog(null,"Indicate a Bus ID to modify");
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
                        if(!comboxOriginCityFk.getSelectedItem().toString().equals(comboxDestinyCityFk.getSelectedItem().toString())){
                            Date current = new Date();
                            if(mainframe.DateChooserTripDate.getDate().getTime()+86000000 > current.getTime()){
                                tripMod.setTripDate(new java.sql.Date(mainframe.DateChooserTripDate.getDate().getTime()));
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
                                setTableResults(tripDAO.getAllTrips(), tripheaders, mainframe.tableTrips);
                                hideElements();  
                                op = 0;
                              
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
                    
                    if(!mainframe.textFieldTripID.getText().equals("") || !mainframe.textFieldTripsPrice.getText().equals("") || mainframe.DateChooserTripDate.getDate() != null || !comboxOriginCityFk.getSelectedItem().toString().equals("Todas las Ciudades") || !comboxDestinyCityFk.getSelectedItem().toString().equals("Todas las Ciudades") || !comboxEmpNumFk.getSelectedItem().toString().equals("0") || !comboxBusIDFk.getSelectedItem().toString().equals("0")){
                        
                        
                        if(mainframe.textFieldTripID.getText().equals("")){
                            tripMod.setTripID(-1);
                        } else {
                            tripMod.setTripID(Integer.parseInt(mainframe.textFieldTripID.getText()));
                        }
                        
                    
                   
                        if(mainframe.DateChooserTripDate.getDate() == null){
                            tripMod.setTripDate(null);
                        } else {
                            tripMod.setTripDate(new java.sql.Date(mainframe.DateChooserTripDate.getDate().getTime()));
                        }

                        if(mainframe.textFieldTripsPrice.getText().equals("")){
                            tripMod.setPrice(-1);
                        } else {
                           tripMod.setPrice(Integer.parseInt(mainframe.textFieldTripsPrice.getText()));
                        }

                        tripMod.setOriginCityFk(comboxOriginCityFk.getSelectedItem().toString());
                        tripMod.setDestinyCityFk(comboxDestinyCityFk.getSelectedItem().toString());
                        tripMod.setEmployeeNumFk(Integer.parseInt(comboxEmpNumFk.getSelectedItem().toString()));
                        tripMod.setBusIDFk(Integer.parseInt(comboxBusIDFk.getSelectedItem().toString()));
                        setTableResults(tripDAO.getTripsByFilter(tripMod), tripheaders, mainframe.tableTrips);
                       
                        
                    } else {
                        setTableResults(tripDAO.getAllTrips(), tripheaders, mainframe.tableTrips);
                    }   
                    hideElements();
                    op = 0;
                    
                    break;
                case 23:
                    
                    if(!mainframe.textFieldTripID.getText().equals("") && !mainframe.textFieldTripsPrice.getText().equals("") && mainframe.DateChooserTripDate.getDate() != null && !comboxOriginCityFk.getSelectedItem().toString().equals("Todas las Ciudades") && !comboxDestinyCityFk.getSelectedItem().toString().equals("Todas las Ciudades") && !comboxEmpNumFk.getSelectedItem().toString().equals("0") && !comboxBusIDFk.getSelectedItem().toString().equals("0")){
                        if(!comboxOriginCityFk.getSelectedItem().toString().equals(comboxDestinyCityFk.getSelectedItem().toString())){
                            Date current = new Date();
                            if(mainframe.DateChooserTripDate.getDate().getTime()+86000000 > current.getTime()){
                                tripMod.setTripDate(new java.sql.Date(mainframe.DateChooserTripDate.getDate().getTime()));
                                tripMod.setPrice(Integer.parseInt(mainframe.textFieldTripsPrice.getText()));
                                tripMod.setOriginCityFk(comboxOriginCityFk.getSelectedItem().toString());
                                tripMod.setDestinyCityFk(comboxDestinyCityFk.getSelectedItem().toString());
                                tripMod.setEmployeeNumFk(Integer.parseInt(comboxEmpNumFk.getSelectedItem().toString()));
                                tripMod.setBusIDFk(Integer.parseInt(comboxBusIDFk.getSelectedItem().toString()));
                                tripMod.setTripID(Integer.parseInt(mainframe.textFieldTripID.getText()));
                                tripDAO.update(tripMod);
                                mainframe.textFieldTripID.setText("");
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
                              JOptionPane.showMessageDialog(null,"A trip cannot be modified with a date in the past");
                            }  
                        } else {
                            JOptionPane.showMessageDialog(null,"Origin City and Destiny City cannot be the same");
                        }
                          
                        
                    } else {
                        JOptionPane.showMessageDialog(null,"To modify a Trip all fields must be populated\n"
                                + "Make sure the date is entered with the correct format. Better use the date chooser");
                    }
                    
                    break;
                case 24: 
                    if(!mainframe.tableTrips.getSelectionModel().isSelectionEmpty()){
                        
                        int response = JOptionPane.showConfirmDialog(mainframe.btnGo, "Are you sure you want to delete the record?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(response == JOptionPane.YES_OPTION ){
                             tripMod.setTripID(Integer.parseInt(mainframe.tableTrips.getModel().getValueAt(mainframe.tableTrips.getSelectedRow(), 0).toString()));
                             tripDAO.delete(tripMod.getTripID());
                             mainframe.textFieldTripID.setText("");
                             mainframe.DateChooserTripDate.setDate(null);
                             mainframe.textFieldTripsPrice.setText("");
                             comboxOriginCityFk.setSelectedIndex(0);
                             comboxDestinyCityFk.setSelectedIndex(0);
                             comboxEmpNumFk.setSelectedIndex(0);
                             comboxBusIDFk.setSelectedIndex(0);
                             hideElements();
                             op = 0;
                             setTableResults(tripDAO.getAllTrips(), tripheaders, mainframe.tableTrips);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Select one register from the table to delete it");
                    }
                    break;
                //CRUD actions for Reservations
                case 25:
                    
                    if(!comboxPassIdFkRes.getSelectedItem().toString().equals("0") && !comboxTripIdFk.getSelectedItem().toString().equals("0")){ 
                        
                        resMod.setPassengerIDFk(Long.parseLong(comboxPassIdFkRes.getSelectedItem().toString()));
                        resMod.setTripIDFk(Integer.parseInt(comboxTripIdFk.getSelectedItem().toString()));
                        resDAO.insertReservation(resMod);
                        comboxPassIdFkRes.setSelectedIndex(0);
                        comboxTripIdFk.setSelectedIndex(0);
                        hideElements();  
                        op = 0;
                        setTableResults(resDAO.getAllReservations(), reservationheaders, mainframe.tableReservations);
                    } else {
                        JOptionPane.showMessageDialog(null,"To create a Reservation all fields must be selected");
                    }
                   
                    break;
                
                case 26:  
                    
                    if(!mainframe.textFieldResNum.getText().equals("") || !comboxPassIdFkRes.getSelectedItem().toString().equals("0") || !comboxTripIdFk.getSelectedItem().toString().equals("0")){
                        
                        
                        if(mainframe.textFieldResNum.getText().equals("")){
                            resMod.setReservationNum(-1);
                        } else {
                            resMod.setReservationNum(Integer.parseInt(mainframe.textFieldResNum.getText()));
                        }
                        
                        resMod.setPassengerIDFk(Long.parseLong(comboxPassIdFkRes.getSelectedItem().toString()));
                        resMod.setTripIDFk(Integer.parseInt(comboxTripIdFk.getSelectedItem().toString()));
                        
               
                        setTableResults(resDAO.getReservationsByFilter(resMod), reservationheaders, mainframe.tableReservations);
                        
                    } else {
                        setTableResults(resDAO.getAllReservations(), reservationheaders, mainframe.tableReservations);
                    }   
                    hideElements();
                    op = 0;
                    
                    break;
                case 27:
                    
                    if(!mainframe.textFieldResNum.getText().equals("") && !comboxPassIdFkRes.getSelectedItem().toString().equals("0") && !comboxTripIdFk.getSelectedItem().toString().equals("0")){ 
                        
                        resMod.setReservationNum(Integer.parseInt(mainframe.textFieldResNum.getText()));
                        resMod.setPassengerIDFk(Long.parseLong(comboxPassIdFkRes.getSelectedItem().toString()));
                        resMod.setTripIDFk(Integer.parseInt(comboxTripIdFk.getSelectedItem().toString()));
                        resDAO.update(resMod);
                        comboxPassIdFkRes.setSelectedIndex(0);
                        comboxTripIdFk.setSelectedIndex(0);
                        mainframe.textFieldResNum.setText("");
                        hideElements();  
                        op = 0;
                        setTableResults(resDAO.getAllReservations(), reservationheaders, mainframe.tableReservations);
                    } else {
                        JOptionPane.showMessageDialog(null,"To Modify a Reservation all fields must be selected and populated");
                    }
                   
                    break;
                    
                case 28: 
                    if(!mainframe.tableReservations.getSelectionModel().isSelectionEmpty()){
                        
                        int response = JOptionPane.showConfirmDialog(mainframe.btnGo, "Are you sure you want to delete the record?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(response == JOptionPane.YES_OPTION ){
                            
                            
                             resMod.setReservationNum(Integer.parseInt(mainframe.tableReservations.getModel().getValueAt(mainframe.tableReservations.getSelectedRow(), 0).toString()));
                             resDAO.delete(resMod.getReservationNum());
                             comboxPassIdFkRes.setSelectedIndex(0);
                             comboxTripIdFk.setSelectedIndex(0);
                             mainframe.textFieldResNum.setText("");
                             hideElements();
                             op = 0;
                             setTableResults(resDAO.getAllReservations(), reservationheaders, mainframe.tableReservations);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Select one register from the table to delete it");
                    }
                    break;
            }   
                
                
        } 
       
        
        if(e.getSource() == mainframe.btnResetLookUp){
            resetCombosLookUp();
        }
        
       
        if(e.getSource() == mainframe.btnSearchLookUp){
            tripMod.setTripID(Integer.parseInt(this.comboxTripIDLookUp.getSelectedItem().toString()));
            if(this.comboxTripDateLookUp.getSelectedItem() == null){
                tripMod.setTripDate(null);
            } else {
                SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
                try {
                    tripMod.setTripDate(new java.sql.Date(sdf.parse(this.comboxTripDateLookUp.getSelectedItem().toString()).getTime()));
                } catch (ParseException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
            
            tripMod.setOriginCityFk(this.comboxOriginCityLookUp.getSelectedItem().toString());
            System.out.println(tripMod.getOriginCityFk());
            tripMod.setDestinyCityFk(this.comboxDestinyCityLookUp.getSelectedItem().toString());
            tripMod.setPrice(Integer.parseInt(this.comboxPriceLookUp.getSelectedItem().toString()));
            tripMod.setEmployeeNumFk(Integer.parseInt(this.comboxEmployeeNumLookUp.getSelectedItem().toString()));
            tripMod.setEmployeeNameLU(this.comboxEmployeeNameLookUp.getSelectedItem().toString());
            tripMod.setReservationNumLU(Integer.parseInt(this.comboxReservationNumLookUp.getSelectedItem().toString()));
            tripMod.setBusIDFk(Integer.parseInt(this.comboxBusIdLookUp.getSelectedItem().toString()));
            tripMod.setLuggageIDLU(Integer.parseInt(this.comboxLugIdLookUp.getSelectedItem().toString()));
            tripMod.setLuggageStatusLU(this.comboxLugStatusLookUp.getSelectedItem().toString());
            tripMod.setPassengerIDLU(Long.parseLong(this.comboxPassNumberLookUp.getSelectedItem().toString()));
            tripMod.setPassengerNameLU(this.comboxPassNameLookUp.getSelectedItem().toString());
            
            this.setTableResults(lookUpDAO.getFilteredData(tripMod), lookupheaders, mainframe.tableLookUp);
            
             
            
        }
       
       
       
    }
    
    
    public void resetCombosLookUp(){
        this.comboxTripIDLookUp.setSelectedIndex(0);
        this.comboxTripDateLookUp.setSelectedIndex(0);
        this.comboxOriginCityLookUp.setSelectedItem("All Cities");
        this.comboxDestinyCityLookUp.setSelectedItem("All Cities");
        this.comboxPriceLookUp.setSelectedIndex(0);
        this.comboxEmployeeNumLookUp.setSelectedIndex(0);
        this.comboxEmployeeNameLookUp.setSelectedItem("All Employee Names");
        this.comboxReservationNumLookUp.setSelectedIndex(0);
        this.comboxBusIdLookUp.setSelectedIndex(0);
        this.comboxLugIdLookUp.setSelectedIndex(0);
        this.comboxLugStatusLookUp.setSelectedItem("All");
        this.comboxPassNameLookUp.setSelectedItem("All Passenger Names");
        this.comboxPassNumberLookUp.setSelectedIndex(0);
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
        
        
        mainframe.labelResNum.setVisible(false);
        mainframe.textFieldResNum.setVisible(false);
        mainframe.labelPassIDFkRes.setVisible(false);
        comboxPassIdFkRes.setVisible(false);
        mainframe.labelTripIDFkRes.setVisible(false);
        comboxTripIdFk.setVisible(false);
        mainframe.btnSearchSelectRes.setVisible(false);
        
    }   
  
    
    public void setTableResults(ArrayList<SuperModel> models, String[] headers, JTable table){
            table.removeAll();
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.setColumnIdentifiers(headers);
            table.setModel(tableModel);
            for(int i=0; i<models.size(); i++){
                tableModel.addRow(models.get(i).toArray(this.currEntity));
            }
    }
    
    public void resetComboboxesTrips(){
        
        InitialDataComboBoxes initialData = new InitialDataComboBoxes();
        
        //Reset combobox en Trips para selección de employeeNumFk
        this.comboxEmpNumFk.setModel(new DefaultComboBoxModel<>(initialData.getEmployees().toArray(new SuperModel[initialData.getEmployees().size()])));
        //Reset combobox en Trips para selección de originCityFk
        this.comboxOriginCityFk.setModel(new DefaultComboBoxModel<>(initialData.getCities().toArray(new SuperModel[initialData.getCities().size()])));
        //Reset combobox en Trips para selección de destinyCityFk
        this.comboxDestinyCityFk.setModel(new DefaultComboBoxModel<>(initialData.getCities().toArray(new SuperModel[initialData.getCities().size()])));
        //Reset combobox en Trips para selección de busIDFK;
        this.comboxBusIDFk.setModel(new DefaultComboBoxModel<>(initialData.getBuses().toArray(new SuperModel[initialData.getBuses().size()])));

    }
    
    public void resetComboboxesLuggage(){
        InitialDataComboBoxes initialData = new InitialDataComboBoxes();
        this.comboxPassIdFk.setModel(new DefaultComboBoxModel<>(initialData.getPassengers().toArray(new SuperModel[initialData.getPassengers().size()])));
    }
    
    public void resetComboboxesReservations(){
        InitialDataComboBoxes initialData = new InitialDataComboBoxes();
        
        //Reset combobox en Reservation para selección de passengerIDFk
        this.comboxPassIdFkRes.setModel(new DefaultComboBoxModel<>(initialData.getPassengers().toArray(new SuperModel[initialData.getPassengers().size()])));
        //Reset combobox en Reservation para selección de tripIDFk
        this.comboxTripIdFk.setModel(new DefaultComboBoxModel<>(initialData.getTrips().toArray(new SuperModel[initialData.getTrips().size()])));
    }
    
    
    
    
    
    
    public void setComboboxIndexFromTableSelection(String selectedData, JComboBox combobox){
        for(int i = 0; i < combobox.getItemCount(); i++ ){
            if(combobox.getItemAt(i).toString().equals(selectedData)){
                combobox.setSelectedIndex(i);
            }
        }
    }
    
    
    public Integer[] resizeSortIntegerArray(Integer[] arr){
            int iterator = 0;
            while(arr[iterator] != null){
                iterator++;
            }
            Integer[] tempArray = new Integer[iterator];
            System.arraycopy(arr, 0, tempArray, 0, iterator);
            Arrays.sort(tempArray);

            return tempArray;
            
    }
    
    
    public Long[] resizeSortLongArray(Long[] arr){
            int iterator = 0;
            while(arr[iterator] != null){
                iterator++;
            }
            Long[] tempArray = new Long[iterator];
            System.arraycopy(arr, 0, tempArray, 0, iterator);
            Arrays.sort(tempArray);

            return tempArray;
            
    }
    
    
    public Date[] resizeSortDateArray(Date[] arr){
            int iterator = 0;
            while(arr[iterator] != null){
                iterator++;
            }
            Date[] tempArray = new Date[iterator];
            System.arraycopy(arr, 0, tempArray, 0, iterator);
            Arrays.sort(tempArray);

            return tempArray;
    }
    
    public String[] resizeSortStringArray(String[] arr){
            int iterator = 0;
            while(arr[iterator] != null){
                iterator++;
            }
            String[] tempArray = new String[iterator];
            System.arraycopy(arr, 0, tempArray, 0, iterator);
            
            String tempString = null;
            
            for(int i = 0; i<tempArray.length;i++){
                for(int j = 1; j<tempArray.length-i;i++){
                    if(tempArray[j-1].compareToIgnoreCase(tempArray[j]) > 0){
                        tempString =  tempArray[j-1];
                        tempArray[j-1] = tempArray[j];
                        tempArray[j] = tempString;
                                
                    }
                }
            }
           
            return tempArray;
           
    }
    
    public void setComboboxesLookUp(JComboBox comboxTripIDLookUp, JComboBox comboxTripDateLookUp, JComboBox comboxOriginCityLookUp, JComboBox comboxDestinyCityLookUp, JComboBox comboxPriceLookUp, JComboBox comboxEmployeeNumLookUp, JComboBox comboxEmployeeNameLookUp, JComboBox comboxReservationNumLookUp, JComboBox comboxBusIdLookUp, JComboBox comboxLugIdLookUp, JComboBox comboxLugStatusLookUp, JComboBox comboxPassNameLookUp, JComboBox comboxPassNumberLookUp){
            InitialDataComboBoxes initialData = new InitialDataComboBoxes();
            Integer[] trpIDs = new Integer[initialData.getDataList().size()];   
            for(int i = 0; i<initialData.getDataList().size();i++){
                if(i==0){
                    trpIDs[0] = ((TripModel)initialData.getDataList().get(i)).getTripID();
                } else if (i>0){
                       for(int j = 0; j<=i;j++) {
                            if(trpIDs[j] != null){
                                if(trpIDs[j] == ((TripModel)initialData.getDataList().get(i)).getTripID()){
                                    break;
                                } 
                            } else {
                                if(trpIDs[j-1] == null || trpIDs[j-1] == ((TripModel)initialData.getDataList().get(i)).getTripID()){
                                    trpIDs[j] = null;
                                } else {
                                    trpIDs[j] = ((TripModel)initialData.getDataList().get(i)).getTripID();
                                }
                            }
                       }    
                }
            }
            trpIDs = resizeSortIntegerArray(trpIDs);
            this.comboxTripIDLookUp.setModel(new DefaultComboBoxModel(trpIDs));
            
        
       
        //Combobox creation in LookUp for TripDate
        Date[] trpDate = new Date[initialData.getDataList().size()];  
        for(int i = 0; i<initialData.getDataList().size();i++){
            if(i==0){
                trpDate[0] = ((TripModel)initialData.getDataList().get(i)).getTripDate();
            } else if (i>0){
                   for(int j = 0; j<=i;j++) {
                        if(trpDate[j] != null){
                            if(trpDate[j].compareTo(((TripModel)initialData.getDataList().get(i)).getTripDate()) == 0){
                                break;
                            } 
                        } else {
                            if(trpDate[j-1] == null || trpDate[j-1].compareTo(((TripModel)initialData.getDataList().get(i)).getTripDate()) == 0){
                                trpDate[j] = null;
                            } else {
                                trpDate[j] = ((TripModel)initialData.getDataList().get(i)).getTripDate();
                            }
                        }
                   }    
            }
        }
        trpDate = resizeSortDateArray(trpDate);
        trpDate[0] = null;
        this.comboxTripDateLookUp.setModel(new DefaultComboBoxModel(trpDate));
        
        
        
        
        //Combobox creation in LookUp for OriginCity
        String[] originCities = new String[initialData.getDataList().size()];  
        for(int i = 0; i<initialData.getDataList().size();i++){
            if(i==0){
                originCities[0] = ((TripModel)initialData.getDataList().get(i)).getOriginCityFk();
            } else if (i>0){
                   for(int j = 0; j<=i;j++) {
                        if(originCities[j] != null){
                            if(originCities[j].equals(((TripModel)initialData.getDataList().get(i)).getOriginCityFk())){
                                break;
                            } 
                        } else {
                            if(originCities[j-1] == null || originCities[j-1].equals(((TripModel)initialData.getDataList().get(i)).getOriginCityFk())){
                                originCities[j] = null;
                            } else {
                                originCities[j] = ((TripModel)initialData.getDataList().get(i)).getOriginCityFk();
                            }
                        }
                   }    
            }
        }
        originCities = resizeSortStringArray(originCities);
        this.comboxOriginCityLookUp.setModel(new DefaultComboBoxModel(originCities));
     
        
        
        
        //Combobox creation in LookUp for DestinyCity
        String[] destinyCities = new String[initialData.getDataList().size()];  
        for(int i = 0; i<initialData.getDataList().size();i++){
            if(i==0){
                destinyCities[0] = ((TripModel)initialData.getDataList().get(i)).getDestinyCityFk();
            } else if (i>0){
                   for(int j = 0; j<=i;j++) {
                        if(destinyCities[j] != null){
                            if(destinyCities[j].equals(((TripModel)initialData.getDataList().get(i)).getDestinyCityFk())){
                                break;
                            } 
                        } else {
                            if(destinyCities[j-1] == null || destinyCities[j-1].equals(((TripModel)initialData.getDataList().get(i)).getDestinyCityFk())){
                                destinyCities[j] = null;
                            } else {
                                destinyCities[j] = ((TripModel)initialData.getDataList().get(i)).getDestinyCityFk();
                            }
                        }
                   }    
            }
        }
        destinyCities = resizeSortStringArray(destinyCities);
        this.comboxDestinyCityLookUp.setModel(new DefaultComboBoxModel(destinyCities));
        
        
        
        
        
        //Combobox creation in LookUp for Price
        Integer[] prices = new Integer[initialData.getDataList().size()];
            for(int i = 0; i<initialData.getDataList().size();i++){
                if(i==0){
                    prices[0] = ((TripModel)initialData.getDataList().get(i)).getPrice();
                } else if (i>0){
                       for(int j = 0; j<=i;j++) {
                            if(prices[j] != null){
                                if(prices[j] == ((TripModel)initialData.getDataList().get(i)).getPrice()){
                                    break;
                                } 
                            } else {
                                if(prices[j-1] == null || prices[j-1] == ((TripModel)initialData.getDataList().get(i)).getPrice()){
                                    prices[j] = null;
                                } else {
                                    prices[j] = ((TripModel)initialData.getDataList().get(i)).getPrice();
                                }
                            }
                       }    
                }
            }
        prices = resizeSortIntegerArray(prices);
        this.comboxPriceLookUp.setModel(new DefaultComboBoxModel(prices));
        
        
        
        
        //Combobox creation in LookUp for EmpNum
        Integer[] empNums = new Integer[initialData.getDataList().size()];   
        for(int i = 0; i<initialData.getDataList().size();i++){
            if(i==0){
                empNums[0] = ((TripModel)initialData.getDataList().get(i)).getEmployeeNumFk();
            } else if (i>0){
                   for(int j = 0; j<=i;j++) {
                        if(empNums[j] != null){
                            if(empNums[j] == ((TripModel)initialData.getDataList().get(i)).getEmployeeNumFk()){
                                break;
                            } 
                        } else {
                            if(empNums[j-1] == null || empNums[j-1] == ((TripModel)initialData.getDataList().get(i)).getEmployeeNumFk()){
                                empNums[j] = null;
                            } else {
                                empNums[j] = ((TripModel)initialData.getDataList().get(i)).getEmployeeNumFk();
                            }
                        }
                   }    
            }
        }
        empNums = resizeSortIntegerArray(empNums);
        this.comboxEmployeeNumLookUp.setModel(new DefaultComboBoxModel(empNums));
        
        
        
        
        
        
        //Combobox creation in LookUp for EmpName
        String[] empNames = new String[initialData.getDataList().size()];  
        for(int i = 0; i<initialData.getDataList().size();i++){
            if(i==0){
                empNames[0] = ((TripModel)initialData.getDataList().get(i)).getEmployeeNameLU();
            } else if (i>0){
                   for(int j = 0; j<=i;j++) {
                        if(empNames[j] != null){
                            if(empNames[j].equals(((TripModel)initialData.getDataList().get(i)).getEmployeeNameLU())){
                                break;
                            } 
                        } else {
                            if(empNames[j-1] == null || empNames[j-1].equals(((TripModel)initialData.getDataList().get(i)).getEmployeeNameLU())){
                                empNames[j] = null;
                            } else {
                                empNames[j] = ((TripModel)initialData.getDataList().get(i)).getEmployeeNameLU();
                            }
                        }
                   }    
            }
        }
        empNames = resizeSortStringArray(empNames);
        this.comboxEmployeeNameLookUp.setModel(new DefaultComboBoxModel(empNames));



        
        
        //Combobox creation in LookUp for ResNum 
        Integer[] resNums = new Integer[initialData.getDataList().size()]; 
        for(int i = 0; i<initialData.getDataList().size();i++){
            if(i==0){
                resNums[0] = ((TripModel)initialData.getDataList().get(i)).getReservationNumLU();
            } else if (i>0){
                   for(int j = 0; j<=i;j++) {
                        if(resNums[j] != null){
                            if(resNums[j] == ((TripModel)initialData.getDataList().get(i)).getReservationNumLU()){
                                break;
                            } 
                        } else {
                            if(resNums[j-1] == null || resNums[j-1] == ((TripModel)initialData.getDataList().get(i)).getReservationNumLU()){
                                resNums[j] = null;
                            } else {
                                resNums[j] = ((TripModel)initialData.getDataList().get(i)).getReservationNumLU();
                            }
                        }
                   }    
            }
        }
        resNums = resizeSortIntegerArray(resNums);
        this.comboxReservationNumLookUp.setModel(new DefaultComboBoxModel(resNums));
        
        
        
        
        //Combobox creation in LookUp for BusID
        Integer[] BusIds = new Integer[initialData.getDataList().size()];
            for(int i = 0; i<initialData.getDataList().size();i++){
                if(i==0){
                    BusIds[0] = ((TripModel)initialData.getDataList().get(i)).getBusIDFk();
                } else if (i>0){
                       for(int j = 0; j<=i;j++) {
                            if(BusIds[j] != null){
                                if(BusIds[j] == ((TripModel)initialData.getDataList().get(i)).getBusIDFk()){
                                    break;
                                } 
                            } else {
                                if(BusIds[j-1] == null || BusIds[j-1] == ((TripModel)initialData.getDataList().get(i)).getBusIDFk()){
                                    BusIds[j] = null;
                                } else {
                                    BusIds[j] = ((TripModel)initialData.getDataList().get(i)).getBusIDFk();
                                }
                            }
                       }    
                }
            }
        BusIds = resizeSortIntegerArray(BusIds);
        this.comboxBusIdLookUp.setModel(new DefaultComboBoxModel(BusIds));


        
        //Combobox creation in LookUp for luggageIDLU
        Integer[] lugIds = new Integer[initialData.getDataList().size()];
            for(int i = 0; i<initialData.getDataList().size();i++){
                if(i==0){
                    lugIds[0] = ((TripModel)initialData.getDataList().get(i)).getLuggageIDLU();
                } else if (i>0){
                       for(int j = 0; j<=i;j++) {
                            if(lugIds[j] != null){
                                if(lugIds[j] == ((TripModel)initialData.getDataList().get(i)).getLuggageIDLU()){
                                    break;
                                } 
                            } else {
                                if(lugIds[j-1] == null || lugIds[j-1] == ((TripModel)initialData.getDataList().get(i)).getLuggageIDLU()){
                                    lugIds[j] = null;
                                } else {
                                    lugIds[j] = ((TripModel)initialData.getDataList().get(i)).getLuggageIDLU();
                                }
                            }
                       }    
                }
            }
        lugIds = resizeSortIntegerArray(lugIds);
        this.comboxLugIdLookUp.setModel(new DefaultComboBoxModel(lugIds));
        
        
        
        
        //Combobox creation in LookUp for LugStatus
        String[] lugStatus = new String[initialData.getDataList().size()];  
        for(int i = 0; i<initialData.getDataList().size();i++){
            if(i==0){
                lugStatus[0] = ((TripModel)initialData.getDataList().get(i)).getLuggageStatusLU();
            } else if (i>0){
                   for(int j = 0; j<=i;j++) {
                        if(lugStatus[j] != null){
                            if(lugStatus[j].equals(((TripModel)initialData.getDataList().get(i)).getLuggageStatusLU())){
                                break;
                            } 
                        } else {
                            if(lugStatus[j-1] == null || lugStatus[j-1].equals(((TripModel)initialData.getDataList().get(i)).getLuggageStatusLU())){
                                lugStatus[j] = null;
                            } else {
                                lugStatus[j] = ((TripModel)initialData.getDataList().get(i)).getLuggageStatusLU();
                            }
                        }
                   }    
            }
        }
        lugStatus = resizeSortStringArray(lugStatus);
        this.comboxLugStatusLookUp.setModel(new DefaultComboBoxModel(lugStatus));
        
        
        //Combobox creation in LookUp for PassName
        String[] pasNames = new String[initialData.getDataList().size()];  
        for(int i = 0; i<initialData.getDataList().size();i++){
            if(i==0){
                pasNames[0] = ((TripModel)initialData.getDataList().get(i)).getPassengerNameLU();
            } else if (i>0){
                   for(int j = 0; j<=i;j++) {
                        if(pasNames[j] != null){
                            if(pasNames[j].equals(((TripModel)initialData.getDataList().get(i)).getPassengerNameLU())){
                                break;
                            } 
                        } else {
                            if(pasNames[j-1] == null || pasNames[j-1].equals(((TripModel)initialData.getDataList().get(i)).getPassengerNameLU())){
                                pasNames[j] = null;
                            } else {
                                pasNames[j] = ((TripModel)initialData.getDataList().get(i)).getPassengerNameLU();
                            }
                        }
                   }    
            }
        }
        pasNames = resizeSortStringArray(pasNames);
        this.comboxPassNameLookUp.setModel(new DefaultComboBoxModel(pasNames));
        
        
        
        
        //Combobox creation in LookUp for passengerIDLU
        Long[] passIds = new Long[initialData.getDataList().size()];
            for(int i = 0; i<initialData.getDataList().size();i++){
                if(i==0){
                    passIds[0] = ((TripModel)initialData.getDataList().get(i)).getPassengerIDLU();
                } else if (i>0){
                       for(int j = 0; j<=i;j++) {
                            if(passIds[j] != null){
                                if(passIds[j] == ((TripModel)initialData.getDataList().get(i)).getPassengerIDLU()){
                                    break;
                                } 
                            } else {
                                if(passIds[j-1] == null || passIds[j-1] == ((TripModel)initialData.getDataList().get(i)).getPassengerIDLU()){
                                    passIds[j] = null;
                                } else {
                                    passIds[j] = ((TripModel)initialData.getDataList().get(i)).getPassengerIDLU();
                                }
                            }
                       }    
                }
            }
        passIds = resizeSortLongArray(passIds);
        this.comboxPassNumberLookUp.setModel(new DefaultComboBoxModel(passIds));
            
            
        }
    
    
    
}
   
