package com.crystal.bd_ygo.model.arquetipo;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class ArquetipoDAO {
    @Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbc;
	
	@PostConstruct
	private void initialize() {
		jdbc = new JdbcTemplate(dataSource);
	}
	
	public void inserirArquetipo(Arquetipo arquetipo) {
		String sql = "INSERT INTO arquetipo(arquetiponome)" +
	                 " VALUES (?)";
		Object obj = new Object();
		obj = arquetipo.getArquetipoNome();
		jdbc.update(sql, obj);
	}

	public Arquetipo mostrarArquetipo(String uuid){
		String sql = "SELECT * FROM arquetipo where arquetipoid=?::uuid";
		return Arquetipo.converter(jdbc.queryForMap(sql,uuid));
	}


	public ArrayList<Arquetipo> listarArquetipos(){
		String sql = "SELECT * FROM arquetipo";
		return Arquetipo.converterTodos(jdbc.queryForList(sql));
	}
}
