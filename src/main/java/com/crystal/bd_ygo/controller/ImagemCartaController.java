package com.crystal.bd_ygo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.crystal.bd_ygo.model.carta.Carta;
import com.crystal.bd_ygo.model.carta.CartaService;
import com.crystal.bd_ygo.model.imagem.ImagemCarta;
import com.crystal.bd_ygo.model.imagem.ImagemCartaService;

@Controller
public class ImagemCartaController {

    @Autowired
    private ImagemCartaService imagemService;

    @Autowired
    private CartaService cartaService;

    @GetMapping("/carta/{uuid}/imagem")
    public String formUpload(@PathVariable("uuid") String cartaId, Model model) {
        Carta carta = cartaService.mostrarCarta(cartaId);
        ImagemCarta imagemExistente = imagemService.buscarPorCarta(cartaId);

        model.addAttribute("carta", carta);
        model.addAttribute("imagemExistente", imagemExistente);
        return "carta/formimagem";
    }

    @PostMapping("/carta/{uuid}/imagem")
    public String upload(@PathVariable("uuid") String cartaId,
                         @RequestParam("arquivo") MultipartFile arquivo,
                         Model model) throws IOException {

        if (arquivo.isEmpty()) {
            model.addAttribute("erro", "Selecione um arquivo de imagem.");
            Carta carta = cartaService.mostrarCarta(cartaId);
            model.addAttribute("carta", carta);
            model.addAttribute("imagemExistente", imagemService.buscarPorCarta(cartaId));
            return "carta/formimagem";
        }

        imagemService.uploadImagem(cartaId, arquivo);
        return "redirect:/carta/listar";
    }

    @GetMapping("/carta/{uuid}/imagem/ver")
    public ResponseEntity<Resource> servirImagem(@PathVariable("uuid") String cartaId) throws IOException {
        ImagemCarta imagem = imagemService.buscarPorCarta(cartaId);

        if (imagem == null) {
            return ResponseEntity.notFound().build();
        }

        Path path = Paths.get(imagem.getCaminho());
        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(path);
        String contentType = Files.probeContentType(path);
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @PostMapping("/carta/{uuid}/imagem/remover")
    public String removerImagem(@PathVariable("uuid") String cartaId) throws IOException {
        imagemService.removerPorCarta(cartaId);
        return "redirect:/carta/listar";
    }

    @GetMapping("/carta/imagens")
    public String listarComImagens(Model model) {
        ArrayList<Carta> cartas = cartaService.listarCartas();
        model.addAttribute("cartas", cartas);
        return "carta/imagenslistar";
    }
}
