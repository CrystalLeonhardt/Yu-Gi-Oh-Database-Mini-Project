package com.crystal.bd_ygo.model.nivel;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class NivelDAO {
    @Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbc;
	
	@PostConstruct
	private void initialize() {
		jdbc = new JdbcTemplate(dataSource);
	}
	
	public void inserirNivel(Nivel nivel) {
		String sql = "INSERT INTO nivel(nivelnumero)" +
	                 " VALUES (?)";
		Object obj = new Object();
		obj = nivel.getNivelNumero();
		jdbc.update(sql, obj);
	}

	public Nivel mostrarNivel(String uuid){
		String sql = "SELECT * FROM nivel where nivelid=?::uuid";
		return Nivel.converter(jdbc.queryForMap(sql,uuid));
	}


	public ArrayList<Nivel> listarNivels(){
		String sql = "SELECT * FROM nivel";
		return Nivel.converterTodos(jdbc.queryForList(sql));
	}
}
