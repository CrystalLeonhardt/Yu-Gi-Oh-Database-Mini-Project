package com.crystal.bd_ygo.model.arquetipo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Arquetipo {
    private String arquetipoID, arquetipoNome;

    //form
    public Arquetipo(){}

    //insert
    public Arquetipo(String arquetipoNome){
        this.arquetipoNome = arquetipoNome;
    }

    //select
    public Arquetipo(String arquetipoNome, String arquetipoID){
        this.arquetipoNome = arquetipoNome;
        this.arquetipoID = arquetipoID;
    }

    public String getArquetipoNome(){
        return arquetipoNome;
    }

    public String getArquetipoID(){
        return arquetipoID;
    }

    public void setArquetipoID(String arquetipoID){
        this.arquetipoID = arquetipoID;
    }

    public void setArquetipoNome(String arquetipoNome){
        this.arquetipoNome = arquetipoNome;
    }

     public static Arquetipo converter(Map<String,Object> registro){
        String arquetipoNome = (String) registro.get("arquetiponome");
        UUID ID = (UUID) registro.get("arquetipoid");
        return new Arquetipo(arquetipoNome, ID.toString());
     }

     public static ArrayList<Arquetipo> converterTodos(List<Map<String,Object>> registros){
        ArrayList<Arquetipo> aux = new ArrayList<>();
        for(Map<String,Object> registro : registros){
            aux.add(converter(registro));
        }
        return aux;
    }
}
