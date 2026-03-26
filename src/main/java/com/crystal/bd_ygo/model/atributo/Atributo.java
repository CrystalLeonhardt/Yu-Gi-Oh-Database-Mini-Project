package com.crystal.bd_ygo.model.atributo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Atributo {
    private String atributoID, atributoNome;

    //form
    public Atributo(){}

    //insert
    public Atributo(String atributoNome){
        this.atributoNome = atributoNome;
    }

    //select
    public Atributo(String atributoNome, String atributoID){
        this.atributoNome = atributoNome;
        this.atributoID = atributoID;
    }

    public String getAtributoNome(){
        return atributoNome;
    }

    public String getAtributoID(){
        return atributoID;
    }

    public void setAtributoID(String atributoID){
        this.atributoID = atributoID;
    }

    public void setAtributoNome(String atributoNome){
        this.atributoNome = atributoNome;
    }

     public static Atributo converter(Map<String,Object> registro){
        String atributoNome = (String) registro.get("atributonome");
        UUID ID = (UUID) registro.get("atributoid");
        return new Atributo(atributoNome, ID.toString());
     }

     public static ArrayList<Atributo> converterTodos(List<Map<String,Object>> registros){
        ArrayList<Atributo> aux = new ArrayList<>();
        for(Map<String,Object> registro : registros){
            aux.add(converter(registro));
        }
        return aux;
    }
}
