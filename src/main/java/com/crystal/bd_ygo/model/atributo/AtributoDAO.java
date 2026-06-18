package com.crystal.bd_ygo.model.atributo;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class AtributoDAO {
    @Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbc;
	
	@PostConstruct
	private void initialize() {
		jdbc = new JdbcTemplate(dataSource);
	}
	
	public void inserirAtributo(Atributo atributo) {
		String sql = "INSERT INTO atributo(atributonome)" +
	                 " VALUES (?)";
		Object obj = new Object();
		obj = atributo.getAtributoNome();
		jdbc.update(sql, obj);
	}

	public Atributo mostrarAtributo(String uuid){
		String sql = "SELECT * FROM atributo where atributoid=?::uuid";
		return Atributo.converter(jdbc.queryForMap(sql,uuid));
	}

	public void atualizarAtributo(Atributo atributo, String uuid){
		String sql = "UPDATE atributo " + 
			"SET atributonome = ? WHERE atributoid = ?::uuid";
		Object[] obj = new Object[2];
		obj[0] = atributo.getAtributoNome();
		obj[1] = uuid;
		jdbc.update(sql,obj);
	}

	public void deletarAtributo(String uuid){
		String sql = "DELETE FROM atributo where atributoid=?::uuid";
		jdbc.update(sql,uuid);
	}

	public ArrayList<Atributo> listarAtributos(){
		String sql = "SELECT * FROM atributo";
		return Atributo.converterTodos(jdbc.queryForList(sql));
	}
}
