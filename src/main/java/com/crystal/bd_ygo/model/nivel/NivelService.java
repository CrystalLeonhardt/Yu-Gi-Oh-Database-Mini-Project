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

}
