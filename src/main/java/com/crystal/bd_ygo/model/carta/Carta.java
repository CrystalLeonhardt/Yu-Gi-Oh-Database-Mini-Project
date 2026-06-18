package com.crystal.bd_ygo.model.carta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Carta {
    private String cartaID, cartaNome, descricao, atributo, tipo, arquetipo, nivel;
    private int atk, def;

    //form
    public Carta(){}

    //insert
    public Carta(String cartaNome, String descricao, int atk, int def, String atributo, String tipo, String arquetipo, String nivel){
        this.cartaNome = cartaNome;
        this.descricao = descricao;
        this.atk = atk;
        this.def = def;
        this.atributo = atributo;
        this.tipo = tipo;
        this.arquetipo = arquetipo;
        this.nivel = nivel;
    }

    //select
    public Carta(String cartaNome, String descricao, String cartaID, int atk, int def, String atributo, String tipo, String arquetipo, String nivel){
        this.cartaNome = cartaNome;
        this.descricao = descricao;
        this.cartaID = cartaID;
        this.atk = atk;
        this.def = def;
        this.atributo = atributo;
        this.tipo = tipo;
        this.arquetipo = arquetipo;
        this.nivel = nivel;
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

    public String getAtributo(){
        return atributo;
    }

    public String getTipo(){
        return tipo;
    }

    public String getArquetipo(){
        return arquetipo;
    }

    public String getNivel(){
        return nivel;
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

    public void setAtributo(String atributo){
        this.atributo = atributo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setArquetipo(String arquetipo){
        this.arquetipo = arquetipo;
    }

    public void setNivel(String nivel){
        this.nivel = nivel;
    }

    public static Carta converter(Map<String,Object> registro){
        String cartaNome = (String) registro.get("cartanome");
        String descricao = (String) registro.get("descricao");
        UUID ID = (UUID) registro.get("cartaid");
        int atk = (int) registro.get("atk");
        int def = (int) registro.get("def");
        String atributo = (String) registro.get("atributonome");
        String tipo = (String) registro.get("tiponome");
        String arquetipo = (String) registro.get("arquetiponome");
        String nivel = (String) registro.get("nivelnumero");
        return new Carta(cartaNome,descricao, ID.toString(), atk, def, atributo, tipo, arquetipo, nivel);
    }

     public static ArrayList<Carta> converterTodos(List<Map<String,Object>> registros){
        ArrayList<Carta> aux = new ArrayList<>();
        for(Map<String,Object> registro : registros){
            aux.add(converter(registro));
        }
        return aux;
    }
}
