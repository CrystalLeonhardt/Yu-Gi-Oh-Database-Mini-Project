package com.crystal.bd_ygo.model.tipo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoService {
    @Autowired
    TipoDAO tipoDAO;

    public void inserirTipo(Tipo tipo){
        tipoDAO.inserirTipo(tipo);
    }

    public Tipo mostrarTipo(String uuid){
        return tipoDAO.mostrarTipo(uuid);
    }

    public ArrayList<Tipo> listarTipos(){
        return tipoDAO.listarTipos();
    }

}
