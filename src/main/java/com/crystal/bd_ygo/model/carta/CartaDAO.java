package com.crystal.bd_ygo.model.carta;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class CartaDAO {
    @Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbc;
	
	@PostConstruct
	private void initialize() {
		jdbc = new JdbcTemplate(dataSource);
	}
	
	public void inserirCarta(Carta carta) {
		String sql = "INSERT INTO carta(cartanome, descricao, atk, def)" +
	                 " VALUES (?,?,?,?)";
		Object[] obj = new Object[4];
		obj[0] = carta.getCartaNome();
		obj[1] = carta.getDescricao();
        obj[2] = carta.getAtk();
        obj[3] = carta.getDef();
		jdbc.update(sql, obj);
	}

	public Carta mostrarCarta(String uuid){
		String sql = "SELECT * FROM carta where cartaid=?::uuid";
		return Carta.converter(jdbc.queryForMap(sql,uuid));
	}


	public ArrayList<Carta> listarCartas(){
		String sql = "SELECT * FROM carta";
		return Carta.converterTodos(jdbc.queryForList(sql));
	}
}
