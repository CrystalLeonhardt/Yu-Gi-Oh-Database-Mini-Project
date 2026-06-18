package com.crystal.bd_ygo.model.tipo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Tipo{
    private String tipoID, tipoNome;

    //form
    public Tipo(){}

    //insert
    public Tipo(String tipoNome){
        this.tipoNome = tipoNome;
    }

    //select
    public Tipo(String tipoNome, String tipoID){
        this.tipoNome = tipoNome;
        this.tipoID = tipoID;
    }

    public String getTipoNome(){
        return tipoNome;
    }

    public String getTipoID(){
        return tipoID;
    }

    public void setTipoID(String tipoID){
        this.tipoID = tipoID;
    }

    public void setTipoNome(String tipoNome){
        this.tipoNome = tipoNome;
    }

     public static Tipo converter(Map<String,Object> registro){
        String tipoNome = (String) registro.get("tiponome");
        UUID ID = (UUID) registro.get("tipoid");
        return new Tipo(tipoNome, ID.toString());
     }

     public static ArrayList<Tipo> converterTodos(List<Map<String,Object>> registros){
        ArrayList<Tipo> aux = new ArrayList<>();
        for(Map<String,Object> registro : registros){
            aux.add(converter(registro));
        }
        return aux;
    }
}
