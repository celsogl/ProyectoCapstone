package com.clases.springboot.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clases.springboot.app.Models.service.IMesasService;


@Controller
public class OrdenaController {
    

	@Autowired
    private IMesasService mesasService;

	@RequestMapping(value = "/ventarapida", method = RequestMethod.GET)
	public String listarOrdena(Model model) {
		model.addAttribute("mesas", mesasService.findAll());
		return "/Mesas/listMesas";
	}
}
