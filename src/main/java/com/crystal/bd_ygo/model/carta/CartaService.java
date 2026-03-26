package com.crystal.bd_ygo.model.carta;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaService {
    @Autowired
    CartaDAO cartaDAO;

    public void inserirCarta(Carta carta){
        cartaDAO.inserirCarta(carta);
    }

    public Carta mostrarCarta(String uuid){
        return cartaDAO.mostrarCarta(uuid);
    }

    public ArrayList<Carta> listarCartas(){
        return cartaDAO.listarCartas();
    }
}
