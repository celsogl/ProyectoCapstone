package com.clases.springboot.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clases.springboot.app.Models.service.IUsuarioService;
import com.clases.springboot.app.dto.UsuarioRegistroDTO;

@Controller
@RequestMapping("/createuseradmin")
public class UsuarioRegistroController {
	@Autowired
	private IUsuarioService clienteService;

	@GetMapping
	public String crearUsuario() {
		return "/Usuario/registrarUsuario";
	}

	@ModelAttribute("cliente")
	public UsuarioRegistroDTO retornarNuevoClienteDTO() {
		return new UsuarioRegistroDTO();
	}

	@PostMapping
	public String registrarCuentaDeCliente(@ModelAttribute("cliente") UsuarioRegistroDTO registroDTO, Model model) {
		clienteService.save(registroDTO);
		return "redirect:/listuser";
	}

	public UsuarioRegistroController(IUsuarioService clienteService) {
		super();
		this.clienteService = clienteService;
	}

	
}
