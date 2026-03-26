package com.crystal.bd_ygo.model.atributo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtributoService {
    @Autowired
    AtributoDAO atributoDAO;

    public void inserirAtributo(Atributo atributo){
        atributoDAO.inserirAtributo(atributo);
    }

    public Atributo mostrarAtributo(String uuid){
        return atributoDAO.mostrarAtributo(uuid);
    }

    public ArrayList<Atributo> listarAtributos(){
        return atributoDAO.listarAtributos();
    }

}
