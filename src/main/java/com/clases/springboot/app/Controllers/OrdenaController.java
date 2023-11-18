package com.clases.springboot.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clases.springboot.app.Models.service.IMesasService;
import com.clases.springboot.app.Models.service.IProductosService;


@Controller
public class OrdenaController {
    

	@Autowired
    private IMesasService mesasService;

	@Autowired
	private IProductosService productosService;

	@RequestMapping(value = "/ventarapida", method = RequestMethod.GET)
	public String listarOrdena(Model model) {
		model.addAttribute("mesas", mesasService.findAll());
		model.addAttribute("productos", productosService.findAll());
		return "/Mesas/listMesas";
	}
}
