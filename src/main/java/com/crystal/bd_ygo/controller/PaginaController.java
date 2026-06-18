package com.crystal.bd_ygo.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.crystal.bd_ygo.model.arquetipo.Arquetipo;
import com.crystal.bd_ygo.model.arquetipo.ArquetipoService;
import com.crystal.bd_ygo.model.atributo.Atributo;
import com.crystal.bd_ygo.model.atributo.AtributoService;
import com.crystal.bd_ygo.model.carta.Carta;
import com.crystal.bd_ygo.model.carta.CartaService;
import com.crystal.bd_ygo.model.imagem.ImagemCartaService;
import com.crystal.bd_ygo.model.nivel.Nivel;
import com.crystal.bd_ygo.model.nivel.NivelService;
import com.crystal.bd_ygo.model.tipo.Tipo;
import com.crystal.bd_ygo.model.tipo.TipoService;

@Controller
public class PaginaController {

    @Autowired
    private ApplicationContext context;
	
    @Autowired
    private CartaService cartaService;

	@Autowired
    private AtributoService atributoService;

	@Autowired
    private TipoService tipoService;

	@Autowired
    private ArquetipoService arquetipoService;
	
	@Autowired
	private NivelService nivelService;

	@Autowired
    private ImagemCartaService imagemCartaService;

    @GetMapping("/")
    public String index(){
        return "index";
    }
    
    @GetMapping("/carta")
	public String formCarta(Model model) {
		model.addAttribute("carta",new Carta());
		model.addAttribute("atributos", atributoService.listarAtributos());
    	model.addAttribute("tipos", tipoService.listarTipos());
    	model.addAttribute("arquetipos", arquetipoService.listarArquetipos());
    	model.addAttribute("nivels", nivelService.listarNivels());
		return "carta/formcarta";
	}

    @PostMapping("/carta")
    public String postCarta(@ModelAttribute Carta carta,
                            @RequestParam(value = "arquivo", required = false) MultipartFile arquivo,
                            Model model) throws IOException {
        cartaService.inserirCarta(carta);
        if (arquivo != null && !arquivo.isEmpty()) {
            ArrayList<Carta> todas = cartaService.listarCartas();
            if (!todas.isEmpty()) {
                String novoId = todas.get(todas.size() - 1).getCartaID();
                imagemCartaService.uploadImagem(novoId, arquivo);
            }
        }
		return "carta/sucessocarta";
    }

    @GetMapping("/carta/{uuid}")
	public String verCarta(@PathVariable String uuid, Model model){
		cartaService = context.getBean(CartaService.class);
	    Carta carta = cartaService.mostrarCarta(uuid);
		model.addAttribute("cartaID",carta.getCartaID());
		model.addAttribute("cartaNome",carta.getCartaNome());
		model.addAttribute("descricao",carta.getDescricao());
        model.addAttribute("atk",carta.getAtk());
        model.addAttribute("def",carta.getDef());
		model.addAttribute("atributo", carta.getAtributo());
		model.addAttribute("tipo", carta.getTipo());
		model.addAttribute("arquetipo", carta.getArquetipo());
		model.addAttribute("nivel", carta.getNivel());
		return "carta/paginacarta";
	}

    @GetMapping("/carta/listar")
	public String listarCartas(Model model){
		cartaService = context.getBean(CartaService.class);
		ArrayList<Carta> cartas = (ArrayList<Carta>) cartaService.listarCartas();
		model.addAttribute("cartas",cartas);
		return "carta/cartaslistar";
	}

	@GetMapping("/carta/{uuid}/editar")
	public String formAtualizarCarta(@PathVariable("uuid") String uuid, Model model) {
		CartaService cdao = context.getBean(CartaService.class);
		Carta cartaid = cdao.mostrarCarta(uuid);
		model.addAttribute("carta", cartaid);
		model.addAttribute("atributos", atributoService.listarAtributos());
    	model.addAttribute("tipos", tipoService.listarTipos());
    	model.addAttribute("arquetipos", arquetipoService.listarArquetipos());
   		model.addAttribute("nivels", nivelService.listarNivels());
		return "carta/formupdcarta";
	}

	@PostMapping("/carta/{uuid}/editar")
	public String atualizararCarta(@PathVariable("uuid") String id, 
			                       Model model,
			                       @ModelAttribute Carta cli) {
		CartaService cdao = context.getBean(CartaService.class);
		cdao.atualizarCarta(cli,id);
		return "redirect:/carta/listar";
	}

	@PostMapping("/carta/{uuid}/deletar")
    public String deletarCarta(@PathVariable("uuid") String id, Model model) throws IOException {
        imagemCartaService.removerPorCarta(id);
        CartaService cdao = context.getBean(CartaService.class);
        cdao.deletarCarta(id);
        return "redirect:/carta/listar";
    }

	@GetMapping("/atributo")
	public String formAtributo(Model model) {
		model.addAttribute("atributo",new Atributo());
		return "atributo/formatributo";
	}

	@PostMapping("/atributo")
	public String postAtributo(@ModelAttribute Atributo atributo, Model model) {
		atributoService.inserirAtributo(atributo);
		return "atributo/sucessoatributo";
	}

	@GetMapping("/atributo/{uuid}")
	public String verAtributo(@PathVariable String uuid, Model model){
		atributoService = context.getBean(AtributoService.class);
	    Atributo atributo = atributoService.mostrarAtributo(uuid);
		model.addAttribute("atributoID",atributo.getAtributoID());
		model.addAttribute("atributoNome",atributo.getAtributoNome());
		return "atributo/paginaatributo";
	}

	@GetMapping("/atributo/listar")
	public String listarAtributos(Model model){
		atributoService = context.getBean(AtributoService.class);
		ArrayList<Atributo> atributos = (ArrayList<Atributo>) atributoService.listarAtributos();
		model.addAttribute("atributos",atributos);
		return "atributo/atributoslistar";
	}

	@GetMapping("/atributo/{uuid}/editar")
	public String formAtualizarAtributo(@PathVariable("uuid") String uuid, Model model) {
		AtributoService adao = context.getBean(AtributoService.class);
		Atributo atributoid = adao.mostrarAtributo(uuid);
		model.addAttribute("atributo", atributoid);
		return "atributo/formupdatributo";
	}

	@PostMapping("/atributo/{uuid}/editar")
	public String atualizararAtributo(@PathVariable("uuid") String id, 
			                       Model model,
			                       @ModelAttribute Atributo cli) {
		AtributoService adao = context.getBean(AtributoService.class);
		adao.atualizarAtributo(cli, id);
		return "redirect:/atributo/listar";
	}

	@PostMapping("/atributo/{uuid}/deletar")
	public String deletarAtributo(@PathVariable("uuid") String id, 
			                       Model model) {
		AtributoService adao = context.getBean(AtributoService.class);
		adao.deletarAtributo(id);
		return "redirect:/atributo/listar";
	}

	@GetMapping("/tipo")
	public String formTipo(Model model) {
		model.addAttribute("tipo",new Tipo());
		return "tipo/formtipo";
	}

	@PostMapping("/tipo")
	public String postTipo(@ModelAttribute Tipo tipo, Model model) {
		tipoService.inserirTipo(tipo);
		return "tipo/sucessotipo";
	}

	@GetMapping("/tipo/{uuid}")
	public String verTipo(@PathVariable String uuid, Model model){
		tipoService = context.getBean(TipoService.class);
	    Tipo tipo = tipoService.mostrarTipo(uuid);
		model.addAttribute("tipoID",tipo.getTipoID());
		model.addAttribute("tipoNome",tipo.getTipoNome());
		return "tipo/paginatipo";
	}

	@GetMapping("/tipo/listar")
	public String listarTipos(Model model){
		tipoService = context.getBean(TipoService.class);
		ArrayList<Tipo> tipos = (ArrayList<Tipo>) tipoService.listarTipos();
		model.addAttribute("tipos",tipos);
		return "tipo/tiposlistar";
	}

	@GetMapping("/tipo/{uuid}/editar")
	public String formAtualizarTipo(@PathVariable("uuid") String uuid, Model model) {
		TipoService tdao = context.getBean(TipoService.class);
		Tipo tipoid = tdao.mostrarTipo(uuid);
		model.addAttribute("tipo", tipoid);
		return "tipo/formupdtipo";
	}

	@PostMapping("/tipo/{uuid}/editar")
	public String atualizararTipo(@PathVariable("uuid") String id, 
			                       Model model,
			                       @ModelAttribute Tipo cli) {
		TipoService tdao = context.getBean(TipoService.class);
		tdao.atualizarTipo(cli, id);
		return "redirect:/tipo/listar";
	}

	@PostMapping("/tipo/{uuid}/deletar")
	public String deletarTipo(@PathVariable("uuid") String id, 
			                       Model model) {
		TipoService tdao = context.getBean(TipoService.class);
		tdao.deletarTipo(id);
		return "redirect:/tipo/listar";
	}

	@GetMapping("/arquetipo")
	public String formArquetipo(Model model) {
		model.addAttribute("arquetipo",new Arquetipo());
		return "arquetipo/formarquetipo";
	}

	@PostMapping("/arquetipo")
	public String postArquetipo(@ModelAttribute Arquetipo arquetipo, Model model) {
		arquetipoService.inserirArquetipo(arquetipo);
		return "arquetipo/sucessoarquetipo";
	}

	@GetMapping("/arquetipo/{uuid}")
	public String verArquetipo(@PathVariable String uuid, Model model){
		arquetipoService = context.getBean(ArquetipoService.class);
	    Arquetipo arquetipo = arquetipoService.mostrarArquetipo(uuid);
		model.addAttribute("arquetipoID",arquetipo.getArquetipoID());
		model.addAttribute("arquetipoNome",arquetipo.getArquetipoNome());
		return "arquetipo/paginaarquetipo";
	}

	@GetMapping("/arquetipo/listar")
	public String listarArquetipos(Model model){
		arquetipoService = context.getBean(ArquetipoService.class);
		ArrayList<Arquetipo> arquetipos = (ArrayList<Arquetipo>) arquetipoService.listarArquetipos();
		model.addAttribute("arquetipos",arquetipos);
		return "arquetipo/arquetiposlistar";
	}

	@GetMapping("/arquetipo/{uuid}/editar")
	public String formAtualizarArquetipo(@PathVariable("uuid") String uuid, Model model) {
		ArquetipoService adao = context.getBean(ArquetipoService.class);
		Arquetipo arquetipoid = adao.mostrarArquetipo(uuid);
		model.addAttribute("arquetipo", arquetipoid);
		return "arquetipo/formupdarquetipo";
	}

	@PostMapping("/arquetipo/{uuid}/editar")
	public String atualizararArquetipo(@PathVariable("uuid") String id, 
			                       Model model,
			                       @ModelAttribute Arquetipo cli) {
		ArquetipoService adao = context.getBean(ArquetipoService.class);
		adao.atualizarArquetipo(cli, id);
		return "redirect:/arquetipo/listar";
	}

	@PostMapping("/arquetipo/{uuid}/deletar")
	public String deletarArquetipo(@PathVariable("uuid") String id, 
			                       Model model) {
		ArquetipoService adao = context.getBean(ArquetipoService.class);
		adao.deletarArquetipo(id);
		return "redirect:/arquetipo/listar";
	}

	@GetMapping("/nivel")
	public String formNivel(Model model) {
		model.addAttribute("nivel",new Nivel());
		return "nivel/formnivel";
	}

	@PostMapping("/nivel")
	public String postNivel(@ModelAttribute Nivel nivel, Model model) {
		nivelService.inserirNivel(nivel);
		return "nivel/sucessonivel";
	}

	@GetMapping("/nivel/{uuid}")
	public String verNivel(@PathVariable String uuid, Model model){
		nivelService = context.getBean(NivelService.class);
	    Nivel nivel = nivelService.mostrarNivel(uuid);
		model.addAttribute("nivelID",nivel.getNivelID());
		model.addAttribute("nivelNumero",nivel.getNivelNumero());
		return "nivel/paginanivel";
	}

	@GetMapping("/nivel/listar")
	public String listarNivels(Model model){
		nivelService = context.getBean(NivelService.class);
		ArrayList<Nivel> nivels = (ArrayList<Nivel>) nivelService.listarNivels();
		model.addAttribute("nivels", nivels);
		return "nivel/niveislistar";
	}

	@GetMapping("/nivel/{uuid}/editar")
	public String formAtualizarNivel(@PathVariable("uuid") String uuid, Model model) {
		NivelService ndao = context.getBean(NivelService.class);
		Nivel nivelid = ndao.mostrarNivel(uuid);
		model.addAttribute("nivel", nivelid);
		return "nivel/formupdnivel";
	}

	@PostMapping("/nivel/{uuid}/editar")
	public String atualizararNivel(@PathVariable("uuid") String id, 
			                       Model model,
			                       @ModelAttribute Nivel cli) {
		NivelService ndao = context.getBean(NivelService.class);
		ndao.atualizarNivel(cli, id);
		return "redirect:/nivel/listar";
	}

	@PostMapping("/nivel/{uuid}/deletar")
	public String deletarNivel(@PathVariable("uuid") String id, 
			                       Model model) {
		NivelService ndao = context.getBean(NivelService.class);
		ndao.deletarNivel(id);
		return "redirect:/nivel/listar";
	}
}
