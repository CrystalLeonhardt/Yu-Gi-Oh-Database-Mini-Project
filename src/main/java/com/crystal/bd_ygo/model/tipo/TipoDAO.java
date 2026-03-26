package com.crystal.bd_ygo.model.tipo;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class TipoDAO {
    @Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbc;
	
	@PostConstruct
	private void initialize() {
		jdbc = new JdbcTemplate(dataSource);
	}
	
	public void inserirTipo(Tipo tipo) {
		String sql = "INSERT INTO tipo(tiponome)" +
	                 " VALUES (?)";
		Object obj = new Object();
		obj = tipo.getTipoNome();
		jdbc.update(sql, obj);
	}

	public Tipo mostrarTipo(String uuid){
		String sql = "SELECT * FROM tipo where tipoid=?::uuid";
		return Tipo.converter(jdbc.queryForMap(sql,uuid));
	}


	public ArrayList<Tipo> listarTipos(){
		String sql = "SELECT * FROM tipo";
		return Tipo.converterTodos(jdbc.queryForList(sql));
	}
}
