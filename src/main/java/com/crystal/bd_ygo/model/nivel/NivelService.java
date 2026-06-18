package com.crystal.bd_ygo.model.nivel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NivelService {
    @Autowired
    NivelDAO nivelDAO;

    public void inserirNivel(Nivel nivel){
        nivelDAO.inserirNivel(nivel);
    }

    public Nivel mostrarNivel(String uuid){
        return nivelDAO.mostrarNivel(uuid);
    }

    public ArrayList<Nivel> listarNivels(){
        return nivelDAO.listarNivels();
    }
    
    public void atualizarNivel(Nivel novo, String uuid){
        nivelDAO.atualizarNivel(novo, uuid);
    }

    public void deletarNivel(String uuid){
        nivelDAO.deletarNivel(uuid);        
    }
}
