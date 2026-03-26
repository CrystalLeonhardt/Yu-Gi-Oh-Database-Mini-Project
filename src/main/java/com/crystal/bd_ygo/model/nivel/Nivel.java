package com.crystal.bd_ygo.model.nivel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Nivel {
    private String nivelID;
    public int nivelNumero;

    //form
    public Nivel(){}

    //insert
    public Nivel(int nivelNumero){
        this.nivelNumero = nivelNumero;
    }

    //select
    public Nivel(int nivelNumero, String nivelID){
        this.nivelNumero = nivelNumero;
        this.nivelID = nivelID;
    }

    public int getNivelNumero(){
        return nivelNumero;
    }

    public String getNivelID(){
        return nivelID;
    }

    public void setNivelID(String nivelID){
        this.nivelID = nivelID;
    }

    public void setNivelNumero(int nivelNumero){
        this.nivelNumero = nivelNumero;
    }

     public static Nivel converter(Map<String,Object> registro){
        int nivelNumero = (int) registro.get("nivelnumero");
        UUID ID = (UUID) registro.get("nivelid");
        return new Nivel(nivelNumero, ID.toString());
     }

     public static ArrayList<Nivel> converterTodos(List<Map<String,Object>> registros){
        ArrayList<Nivel> aux = new ArrayList<>();
        for(Map<String,Object> registro : registros){
            aux.add(converter(registro));
        }
        return aux;
    }
}
