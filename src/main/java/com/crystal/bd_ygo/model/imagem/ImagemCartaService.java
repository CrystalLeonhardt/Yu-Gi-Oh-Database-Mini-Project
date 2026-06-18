package com.crystal.bd_ygo.model.imagem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagemCartaService {

    /** Pasta onde os arquivos são gravados no sistema de arquivos. */
    private static final String UPLOAD_DIR = "./uploads/cartas";

    @Autowired
    private ImagemCartaDAO imagemCartaDAO;

    /**
     * Faz o upload do arquivo, persiste o caminho no banco e retorna
     * a entidade salva.  Caso a carta já possua imagem, substitui a existente
     * (mantém o registro one-to-one) e apaga o arquivo antigo.
     *
     * @param cartaId UUID da carta dona da imagem
     * @param arquivo MultipartFile recebido do formulário
     * @return ImagemCarta salva
     * @throws IOException se o arquivo não puder ser gravado
     */
    public ImagemCarta uploadImagem(String cartaId, MultipartFile arquivo) throws IOException {

        // Garante que o diretório exista
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Se já existe imagem para essa carta, deleta o arquivo físico anterior
        ImagemCarta existente = imagemCartaDAO.buscarPorCarta(cartaId);
        if (existente != null) {
            Path arquivoAntigo = Paths.get(existente.getCaminho());
            Files.deleteIfExists(arquivoAntigo);
        }

        // Gera nome único para evitar colisões
        String nomeOriginal = arquivo.getOriginalFilename();
        String nomeArquivo  = UUID.randomUUID() + "_" + nomeOriginal;
        Path   destino      = uploadPath.resolve(nomeArquivo);

        // Salva o arquivo em disco
        Files.copy(arquivo.getInputStream(), destino);

        // Persiste (insert ou update) no banco
        ImagemCarta imagem = new ImagemCarta(cartaId, nomeOriginal, destino.toString());
        imagemCartaDAO.salvar(imagem);

        return imagemCartaDAO.buscarPorCarta(cartaId);
    }

    /** Busca a imagem pelo cartaId. Retorna null se a carta não tiver imagem. */
    public ImagemCarta buscarPorCarta(String cartaId) {
        return imagemCartaDAO.buscarPorCarta(cartaId);
    }

    /** Busca a imagem pelo imagemId. */
    public ImagemCarta buscarPorId(String imagemId) {
        return imagemCartaDAO.buscarPorId(imagemId);
    }

    /** Lista todas as imagens cadastradas. */
    public List<ImagemCarta> listar() {
        return imagemCartaDAO.listar();
    }

    /**
     * Remove a imagem do banco e apaga o arquivo físico.
     *
     * @param cartaId UUID da carta
     */
    public void removerPorCarta(String cartaId) throws IOException {
        ImagemCarta imagem = imagemCartaDAO.buscarPorCarta(cartaId);
        if (imagem != null) {
            Path arquivo = Paths.get(imagem.getCaminho());
            Files.deleteIfExists(arquivo);
            imagemCartaDAO.deletarPorCarta(cartaId);
        }
    }
}
