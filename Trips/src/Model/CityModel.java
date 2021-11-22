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
public class CityModel extends SuperModel {
    
    private String cityName;

    public CityModel(String cityName) {
        this.cityName = cityName;
    }

    public CityModel() {
    }
    
    

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public Object[] toArray(){
        Object[] data = {cityName};
        return data;
    }
    
    @Override
    public String toString(){
        return this.cityName;
    }
    
    
}
