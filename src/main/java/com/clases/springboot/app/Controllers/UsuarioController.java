package com.clases.springboot.app.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import com.clases.springboot.app.Models.service.IUsuarioService;
import com.clases.springboot.app.dto.UsuarioRegistroDTO;


@Controller
@RequestMapping("/createuser")
public class UsuarioController {

	@Autowired
	private IUsuarioService clienteService;

	@GetMapping
	public String crearUsuario() {
		return "signup";
	}

	@ModelAttribute("cliente")
	public UsuarioRegistroDTO retornarNuevoClienteDTO() {
		return new UsuarioRegistroDTO();
	}

	@PostMapping
	public String registrarCuentaDeCliente(@ModelAttribute("cliente") UsuarioRegistroDTO registroDTO) {
		clienteService.save(registroDTO);
		return "redirect:/login?exito";
	}

	
	
	public UsuarioController(IUsuarioService clienteService) {
		super();
		this.clienteService = clienteService;
	}


}