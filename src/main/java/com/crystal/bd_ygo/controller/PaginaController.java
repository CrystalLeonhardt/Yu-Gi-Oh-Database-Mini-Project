package com.crystal.bd_ygo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.crystal.bd_ygo.model.arquetipo.Arquetipo;
import com.crystal.bd_ygo.model.arquetipo.ArquetipoService;
import com.crystal.bd_ygo.model.atributo.Atributo;
import com.crystal.bd_ygo.model.atributo.AtributoService;
import com.crystal.bd_ygo.model.carta.Carta;
import com.crystal.bd_ygo.model.carta.CartaService;
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

    @GetMapping("/")
    public String index(){
        return "index";
    }
    
    @GetMapping("/carta")
	public String formCarta(Model model) {
		model.addAttribute("carta",new Carta());
		return "carta/formcarta";
	}

    @PostMapping("/carta")
	public String postCarta(@ModelAttribute Carta carta, Model model) {
		cartaService.inserirCarta(carta);
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
		return "carta/paginacarta";
	}

    @GetMapping("/carta/listar")
	public String listarCartas(Model model){
		cartaService = context.getBean(CartaService.class);
		ArrayList<Carta> cartas = (ArrayList<Carta>) cartaService.listarCartas();
		model.addAttribute("cartas",cartas);
		return "carta/cartaslistar";
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
		model.addAttribute("nivels",nivels);
		return "nivel/niveislistar";
	}

}
