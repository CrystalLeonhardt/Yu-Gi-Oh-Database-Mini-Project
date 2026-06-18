package com.crystal.bd_ygo.model.usuario;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void inserirUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new RuntimeException("Nome é obrigatório!");
        }
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            throw new RuntimeException("Senha é obrigatória!");
        }
        // Encripta a senha antes de salvar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioDAO.inserirUsuario(usuario);
    }

    public Usuario mostrarUsuario(String uuid) {
        return usuarioDAO.mostrarUsuario(uuid);
    }

    public ArrayList<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }
}
