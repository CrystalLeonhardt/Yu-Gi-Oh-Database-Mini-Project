package com.crystal.bd_ygo.model.arquetipo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArquetipoService {
    @Autowired
    ArquetipoDAO arquetipoDAO;

    public void inserirArquetipo(Arquetipo arquetipo){
        arquetipoDAO.inserirArquetipo(arquetipo);
    }

    public Arquetipo mostrarArquetipo(String uuid){
        return arquetipoDAO.mostrarArquetipo(uuid);
    }

    public ArrayList<Arquetipo> listarArquetipos(){
        return arquetipoDAO.listarArquetipos();
    }

}
