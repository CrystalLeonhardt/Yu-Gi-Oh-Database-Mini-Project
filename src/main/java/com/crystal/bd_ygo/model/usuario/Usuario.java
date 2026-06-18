package com.crystal.bd_ygo.model.usuario;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.UUID;

public class Usuario {
    private String usuarioID;
    private String nome;
    private String password;

    // form
    public Usuario() {}

    // insert
    public Usuario(String nome, String password) {
        this.nome = nome;
        this.password = password;
    }

    // select
    public Usuario(String usuarioID, String nome, String password) {
        this.usuarioID = usuarioID;
        this.nome = nome;
        this.password = password;
    }

    public String getUsuarioID() { return usuarioID; }
    public String getNome()      { return nome; }
    public String getPassword()  { return password; }

    public void setUsuarioID(String usuarioID) { this.usuarioID = usuarioID; }
    public void setNome(String nome)           { this.nome = nome; }
    public void setPassword(String password)   { this.password = password; }

    public static Usuario converter(Map<String, Object> registro) {
        UUID id = (UUID) registro.get("usuarioid");
        String nome     = (String) registro.get("nome");
        String password = (String) registro.get("password");
        return new Usuario(id.toString(), nome, password);
    }

    public static ArrayList<Usuario> converterTodos(List<Map<String, Object>> registros) {
        ArrayList<Usuario> aux = new ArrayList<>();
        for (Map<String, Object> registro : registros) {
            aux.add(converter(registro));
        }
        return aux;
    }
}
