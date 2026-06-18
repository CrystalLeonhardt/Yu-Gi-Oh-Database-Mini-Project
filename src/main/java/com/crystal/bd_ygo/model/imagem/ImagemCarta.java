package com.crystal.bd_ygo.model.imagem;

import java.util.Map;
import java.util.UUID;

public class ImagemCarta {

    private String imagemId;
    private String cartaId;
    private String nomeOriginal;
    private String caminho;

    // construtor vazio (binding)
    public ImagemCarta() {}

    // construtor para inserção
    public ImagemCarta(String cartaId, String nomeOriginal, String caminho) {
        this.cartaId     = cartaId;
        this.nomeOriginal = nomeOriginal;
        this.caminho     = caminho;
    }

    // construtor para seleção
    public ImagemCarta(String imagemId, String cartaId, String nomeOriginal, String caminho) {
        this.imagemId    = imagemId;
        this.cartaId     = cartaId;
        this.nomeOriginal = nomeOriginal;
        this.caminho     = caminho;
    }

    // -- conversor de linha JDBC --
    public static ImagemCarta converter(Map<String, Object> row) {
        UUID iid = (UUID) row.get("imagemid");
        UUID cid = (UUID) row.get("cartaid");
        return new ImagemCarta(
            iid.toString(),
            cid.toString(),
            (String) row.get("nomeoriginal"),
            (String) row.get("caminho")
        );
    }

    // -- getters / setters --
    public String getImagemId()           { return imagemId; }
    public void   setImagemId(String id)  { this.imagemId = id; }

    public String getCartaId()            { return cartaId; }
    public void   setCartaId(String id)   { this.cartaId = id; }

    public String getNomeOriginal()                   { return nomeOriginal; }
    public void   setNomeOriginal(String nomeOriginal){ this.nomeOriginal = nomeOriginal; }

    public String getCaminho()                { return caminho; }
    public void   setCaminho(String caminho)  { this.caminho = caminho; }
}
