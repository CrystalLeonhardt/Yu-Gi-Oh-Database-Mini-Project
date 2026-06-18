package com.crystal.bd_ygo.model.imagem;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class ImagemCartaDAO {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    /** Insere uma nova imagem vinculada a uma carta. */
    public void inserir(ImagemCarta imagem) {
        String sql = "INSERT INTO imagem_carta (cartaid, nomeoriginal, caminho) " +
                     "VALUES (?::uuid, ?, ?)";
        jdbc.update(sql,
                imagem.getCartaId(),
                imagem.getNomeOriginal(),
                imagem.getCaminho());
    }

    /**
     * Atualiza o registro quando a carta já possui imagem (one-to-one).
     * Usa UPDATE para não duplicar.
     */
    public void atualizar(ImagemCarta imagem) {
        String sql = "UPDATE imagem_carta SET nomeoriginal = ?, caminho = ? " +
                     "WHERE cartaid = ?::uuid";
        jdbc.update(sql,
                imagem.getNomeOriginal(),
                imagem.getCaminho(),
                imagem.getCartaId());
    }

    /**
     * Insere ou atualiza conforme a carta já tenha imagem (upsert manual).
     */
    public void salvar(ImagemCarta imagem) {
        if (buscarPorCarta(imagem.getCartaId()) == null) {
            inserir(imagem);
        } else {
            atualizar(imagem);
        }
    }

    /** Retorna a imagem de uma carta específica ou null se não existir. */
    public ImagemCarta buscarPorCarta(String cartaId) {
        String sql = "SELECT imagemid, cartaid, nomeoriginal, caminho " +
                     "FROM imagem_carta WHERE cartaid = ?::uuid";
        try {
            Map<String, Object> row = jdbc.queryForMap(sql, cartaId);
            return ImagemCarta.converter(row);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /** Retorna a imagem pelo seu próprio ID ou null se não existir. */
    public ImagemCarta buscarPorId(String imagemId) {
        String sql = "SELECT imagemid, cartaid, nomeoriginal, caminho " +
                     "FROM imagem_carta WHERE imagemid = ?::uuid";
        try {
            Map<String, Object> row = jdbc.queryForMap(sql, imagemId);
            return ImagemCarta.converter(row);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /** Lista todas as imagens cadastradas. */
    public List<ImagemCarta> listar() {
        String sql = "SELECT imagemid, cartaid, nomeoriginal, caminho FROM imagem_carta";
        List<Map<String, Object>> rows = jdbc.queryForList(sql);
        return rows.stream().map(ImagemCarta::converter).toList();
    }

    /** Remove a imagem de uma carta pelo cartaId. */
    public void deletarPorCarta(String cartaId) {
        jdbc.update("DELETE FROM imagem_carta WHERE cartaid = ?::uuid", cartaId);
    }

    /** Remove a imagem pelo imagemId. */
    public void deletarPorId(String imagemId) {
        jdbc.update("DELETE FROM imagem_carta WHERE imagemid = ?::uuid", imagemId);
    }
}
