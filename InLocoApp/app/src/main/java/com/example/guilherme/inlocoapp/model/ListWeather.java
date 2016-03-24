package com.example.guilherme.inlocoapp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Guilherme on 3/4/2016.
 */
public class ListWeather implements Serializable {
    private ArrayList<Weather> list;

    public ListWeather(){
        list = new ArrayList<Weather>();
    }

    public ArrayList<Weather> getList() {
        return list;
    }
    public int getSize(){
        return list.size();
    }
    public String getName(int i){
        return list.get(i).location.getName();
    }
    public Weather getWeatherByCityName(String cityName){
        Weather resp = null;
        String temp = "";
        for (int i = 0; i < this.list.size(); i++){
            temp = list.get(i).location.getName();
            if (temp.equalsIgnoreCase(cityName)){
                resp = list.get(i);
                break;
            }
        }
        return resp;
    }
    public void addToList(Weather weather){
        this.list.add(weather);
    }
    public Weather get(int i ){
        return this.list.get(i);
    }

}
