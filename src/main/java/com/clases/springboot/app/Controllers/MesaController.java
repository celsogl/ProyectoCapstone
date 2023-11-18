package com.clases.springboot.app.Controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clases.springboot.app.Models.Entity.Mesas;

import com.clases.springboot.app.Models.service.IMesasService;

@Controller
@SessionAttributes("mesas")
public class MesaController {
    
    @Autowired
    private IMesasService mesasService;


    @RequestMapping(value = "/listmesas", method = RequestMethod.GET)
	public String listarMesas(Model model) {
		model.addAttribute("mesas", mesasService.findAll());
		return "/Mesas/listMesasAdmin";
	}


	@RequestMapping(value = "/createmesas")
	public String crearMesas(Map<String, Object> model) {
		Mesas mesas = new Mesas();
		model.put("mesas", mesas);
		return "/Mesas/registrarMesas";
	}

	@RequestMapping(value = "/createmesas", method = RequestMethod.POST)
	public String guardar(Mesas mesas,RedirectAttributes attribute) throws IOException {
			mesasService.save(mesas);
		return "redirect:/listmesas";
	}

	@RequestMapping(value = "/formmesas/{id}")
	public String editarMesas(@PathVariable(value = "id") Long id, Map<String, Object> model) throws IOException {
		Mesas mesas = null;
		if (id > 0) {
			mesas = mesasService.findById(id);
		}
		else {
			return "redirect:/listmesas";
		}
		model.put("mesas", mesas);

		return "/Mesas/registrarMesas";
	}



}
