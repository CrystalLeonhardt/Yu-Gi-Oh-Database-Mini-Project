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
		String sql = "INSERT INTO carta(cartanome, descricao, atk, def, atributoid, tipoid, arquetipoid, nivelid)" +
	                 " VALUES (?,?,?,?,?::uuid,?::uuid,?::uuid,?::uuid)";
		Object[] obj = new Object[8];
		obj[0] = carta.getCartaNome();
		obj[1] = carta.getDescricao();
        obj[2] = carta.getAtk();
        obj[3] = carta.getDef();
		obj[4] = carta.getAtributo();
		obj[5] = carta.getTipo();
		obj[6] = carta.getArquetipo();
		obj[7] = carta.getNivel();
		jdbc.update(sql, obj);
	}

	public void atualizarCarta(Carta carta, String uuid){
		String sql = "UPDATE carta " + 
			"SET cartanome = ?, descricao = ?, atk = ?, def = ?, atributoid = ?::uuid, tipoid = ?::uuid, arquetipoid = ?::uuid, nivelid = ?::uuid WHERE cartaid = ?::uuid";
		Object[] obj = new Object[9];
		obj[0] = carta.getCartaNome();
		obj[1] = carta.getDescricao();
		obj[2] = carta.getAtk();
		obj[3] = carta.getDef();
		obj[4] = carta.getAtributo();
		obj[5] = carta.getTipo();
		obj[6] = carta.getArquetipo();
		obj[7] = carta.getNivel();
		obj[8] = uuid;
		jdbc.update(sql,obj);
	}

	public void deletarCarta(String uuid){
		String sql = "DELETE FROM carta where cartaid=?::uuid";
		jdbc.update(sql,uuid);
	}

	public Carta mostrarCarta(String uuid){
		String sql = "SELECT c.cartaid, c.cartanome, c.descricao, c.atk, c.def, " +
                 	"a.atributonome, t.tiponome, ar.arquetiponome, n.nivelnumero " +
                	 "FROM carta c " +
                	 "JOIN atributo a ON c.atributoid = a.atributoid " +
                	 "JOIN tipo t ON c.tipoid = t.tipoid " +
                	 "JOIN arquetipo ar ON c.arquetipoid = ar.arquetipoid " +
                	 "JOIN nivel n ON c.nivelid = n.nivelid " +
                	 "WHERE c.cartaid = ?::uuid";
		return Carta.converter(jdbc.queryForMap(sql,uuid));
	}

	public ArrayList<Carta> listarCartas(){
		String sql = "SELECT c.cartaid, c.cartanome, c.descricao, c.atk, c.def, " +
                 	"a.atributonome, t.tiponome, ar.arquetiponome, n.nivelnumero " +
                	 "FROM carta c " +
               		"LEFT JOIN atributo a ON c.atributoid = a.atributoid " +
                 	"LEFT JOIN tipo t ON c.tipoid = t.tipoid " +
                 	"LEFT JOIN arquetipo ar ON c.arquetipoid = ar.arquetipoid " +
                 	"LEFT JOIN nivel n ON c.nivelid = n.nivelid";
		return Carta.converterTodos(jdbc.queryForList(sql));
	}
}
