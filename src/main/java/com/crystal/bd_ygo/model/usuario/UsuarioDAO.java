package com.crystal.bd_ygo.model.usuario;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class UsuarioDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    private PasswordEncoder passwordEncoder;

    public void inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome, password) VALUES (?, ?)";

        Object[] obj = new Object[2];
        obj[0] = usuario.getNome();
        obj[1] = passwordEncoder.encode(usuario.getPassword());

        jdbc.update(sql, obj);

        jdbc.update(
            "INSERT INTO perfil_usuario(usuarioid, cargo) " +
            "SELECT usuarioid, 'user' FROM usuario WHERE nome = ?",
            usuario.getNome()
        );
    }

    public Usuario mostrarUsuario(String uuid) {
        String sql = "SELECT * FROM usuario WHERE usuarioid = ?::uuid";
        return Usuario.converter(jdbc.queryForMap(sql, uuid));
    }

    public ArrayList<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM usuario";
        return Usuario.converterTodos(jdbc.queryForList(sql));
    }
}
