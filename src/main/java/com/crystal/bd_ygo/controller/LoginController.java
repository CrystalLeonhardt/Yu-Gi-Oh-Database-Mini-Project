package com.crystal.bd_ygo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crystal.bd_ygo.model.usuario.Usuario;
import com.crystal.bd_ygo.model.usuario.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error",  required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error  != null) model.addAttribute("errorMsg",  "Nome ou senha inválidos.");
        if (logout != null) model.addAttribute("logoutMsg", "Você saiu do sistema.");
        return "login";
    }

    @GetMapping("/usuario")
    public String formUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/formusuario";
    }

    @PostMapping("/usuario")
    public String postUsuario(@ModelAttribute Usuario usuario, Model model) {
        usuarioService.inserirUsuario(usuario);
        return "redirect:/login";
    }
}
