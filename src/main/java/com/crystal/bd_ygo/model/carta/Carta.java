package com.crystal.bd_ygo.model.carta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Carta {
    private String cartaID, cartaNome, descricao;
    private int atk, def;

    //form
    public Carta(){}

    //insert
    public Carta(String cartaNome, String descricao, int atk, int def){
        this.cartaNome = cartaNome;
        this.descricao = descricao;
        this.atk = atk;
        this.def = def;
    }

    //select
    public Carta(String cartaNome, String descricao, String cartaID, int atk, int def){
        this.cartaNome = cartaNome;
        this.descricao = descricao;
        this.cartaID = cartaID;
        this.atk = atk;
        this.def = def;
    }

    public String getCartaNome(){
        return cartaNome;
    }

    public String getDescricao(){
        return descricao;
    }

    public String getCartaID(){
        return cartaID;
    }

    public int getAtk(){
        return atk;
    }

    public int getDef(){
        return def;
    }

    public void setCartaNome(String cartaNome){
        this.cartaNome = cartaNome;
    }

    public void setCartaID(String cartaID){
        this.cartaID = cartaID;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void setAtk(int atk){
        this.atk = atk;
    }

    public void setDef(int def){
        this.def = def;
    }

    public static Carta converter(Map<String,Object> registro){
        String cartaNome = (String) registro.get("cartanome");
        String descricao = (String) registro.get("descricao");
        UUID ID = (UUID) registro.get("cartaid");
        int atk = (int) registro.get("atk");
        int def = (int) registro.get("def");
        return new Carta(cartaNome,descricao, ID.toString(), atk, def);
    }

     public static ArrayList<Carta> converterTodos(List<Map<String,Object>> registros){
        ArrayList<Carta> aux = new ArrayList<>();
        for(Map<String,Object> registro : registros){
            aux.add(converter(registro));
        }
        return aux;
    }
}
